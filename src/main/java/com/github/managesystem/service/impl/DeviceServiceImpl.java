package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.collection.CollectServer;
import com.github.managesystem.collection.handle.decoder.TemperatureDecoder;
import com.github.managesystem.collection.model.CommandEnum;
import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.*;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.mapper.TaskDeviceMapper;
import com.github.managesystem.mapper.TaskMapper;
import com.github.managesystem.model.constant.AttributeEnum;
import com.github.managesystem.model.constant.DeviceStateEnum;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.constant.TaskStateEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.IDeviceControlRecordService;
import com.github.managesystem.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TransformUtils;
import io.netty.channel.ChannelHandlerContext;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.lang.util.ListSet;
import org.nutz.mapl.Mapl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.*;
import java.util.jar.JarEntry;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

    @Autowired
    private TaskDeviceMapper taskDeviceMapper;

    @Autowired
    private TaskMapper taskMapper;

    @Value("${device.img}")
    private String deviceImg;

    @Value("${device.collectspace}")
    private Integer deviceCollectSpace;

    @Autowired
    private IDeviceControlRecordService deviceControlRecordService;

    @Override
    public ListDeviceAdminResp listDeviceAdmin(ListDeviceAdminReq req) {
        ListDeviceAdminResp resp = new ListDeviceAdminResp();
        Page<Device> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(req.getCompanyName())){
            queryWrapper.eq(Device.COMPANY_NAME,req.getCompanyName());
        }
        if(Strings.isNotBlank(req.getDeviceNum())){
            queryWrapper.eq(Device.DEVICE_NUM,req.getDeviceNum());
        }

        IPage<Device> devices = this.page(page, queryWrapper);

        for(Device device : devices.getRecords()){
            resp.getInfos().add(ListDeviceAdminInfo.builder()
                    .companyName(device.getCompanyName())
                    .deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum())
                    .build());
        }
        resp.setTotal(devices.getTotal());
        return resp;
    }

    @Override
    public void deleteDevice(DeleteDeviceReq req) {
        this.remove(new QueryWrapper<Device>().eq(Device.DEVICE_NUM,req.getDeviceNum()));
    }

    @Override
    public void addDevice(AddDeviceReq req) throws CodeException{
        Device one = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()));
        if(one != null){
            throw new CodeException(ResultCode.ERROR_DEVICE);
        }

        Device device = Device.builder().deviceNum(req.getDeviceNum())
                .deviceName(req.getDeviceNum())
                .companyName(req.getCompanyName())
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .collectSpace(deviceCollectSpace)
                .deviceImg(deviceImg)
                .attributeInfo(Json.toJson(AttributeEnum.getDefaultAttribute(),JsonFormat.tidy()))
                .build();

        this.save(device);
    }

    @Override
    public void editDeviceAdmin(EditDeviceAdmin req) {
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()));
        device.setCompanyName(req.getCompanyName());
        device.setModifyTime(LocalDateTime.now());
        this.updateById(device);
    }


    @Override
    public void editDeviceUser(EditDeviceUserReq req) {
        Map<String,String> attributeInfo = new LinkedHashMap<>();
        for(AttributeInfo info : req.getAttributeInfo()){
            attributeInfo.put(info.getCode(),info.getName());
        }
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()), false);

        if(!Objects.equals(req.getCollectSpace(),device.getCollectSpace())){
            //创建控制命令
            DeviceControlRecord build = DeviceControlRecord.builder().deviceNum(device.getDeviceNum())
                    .createTime(LocalDateTime.now())
                    .modifyTime(LocalDateTime.now())
                    .controlType("86")
                    .controlData(TransformUtils.byteToHexString(TransformUtils.shortoByteArray(Short.valueOf(req.getCollectSpace().toString()))))
                    .controlState(0).build();
            deviceControlRecordService.save(build);
        }

        device.setAttributeInfo(Json.toJson(attributeInfo,JsonFormat.tidy()));
        device.setDeviceImg(req.getImg());
        device.setDeviceName(req.getDeviceName());
        device.setCollectSpace(req.getCollectSpace());
        this.updateById(device);

        TaskDevice taskDevice = TaskDevice.builder()
                .deviceName(device.getDeviceName())
                .deviceImg(device.getDeviceImg())
                .attributeInfo(device.getAttributeInfo())
                .collectSpace(device.getCollectSpace())
                .build();

        taskDeviceMapper.update(taskDevice,new QueryWrapper<TaskDevice>()
                .eq(TaskDevice.TASK_NUM,req.getTaskNum()).eq(TaskDevice.DEVICE_NUM,req.getDeviceNum()));

    }

    @Override
    public List<ListDeviceTaskResp> listDeviceTask(ListDeviceTaskReq req, HttpServletRequest request) {

        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        QueryWrapper<TaskDevice> taskDeviceQueryWrapper = new QueryWrapper<>();
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
            taskDeviceQueryWrapper.eq(TaskDevice.COMPANY_NAME,user.getCompanyName());
        }

        List<Device> devices = this.list(queryWrapper);
        Map<String,ListDeviceTaskResp> deviceNums= new HashMap<>();
        for(Device device:devices){
            deviceNums.put(device.getDeviceNum(),ListDeviceTaskResp.builder().deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum()).build());
        }

        if(Strings.isNotBlank(req.getTaskNum())){
            taskDeviceQueryWrapper
                    .ne(TaskDevice.TASK_NUM,req.getTaskNum());
        }

        List<TaskDevice> taskDevices = taskDeviceMapper.selectList(taskDeviceQueryWrapper.ne(TaskDevice.TASK_STATUS, TaskStateEnum.END.value));

        for(TaskDevice taskDevice : taskDevices){
            if(deviceNums.containsKey(taskDevice.getDeviceNum())) {
                deviceNums.remove(taskDevice.getDeviceNum());
            }
        }
        return new ArrayList<>(deviceNums.values());
    }

    @Override
    public void controlDevice(ControlDeviceReq req) throws CodeException {
        this.update(new UpdateWrapper<Device>().set(Device.CONTROL_DATA,Json.toJson(req,JsonFormat.tidy()))
                .eq(Device.DEVICE_NUM,req.getDeviceNum()));

        ChannelHandlerContext ctx = CollectServer.channelHandlerMap.get(req.getDeviceNum());
        if(ctx == null){
            throw new CodeException(ResultCode.ERROR_DEVICE_DISCONNECT);
        }
        ResponseModel resp = new ResponseModel();
        resp.setDevNum(req.getDeviceNum());
        resp.setCommand(CommandEnum.COMMAND_86.getValue());
        List<DeviceControlRecord> records = new ArrayList<>();

        if(Strings.isNotBlank(req.getModelType())){
            records.add(DeviceControlRecord.builder()
                    .controlType(CommandEnum.COMMAND_8A.getValue())
                    .controlData(req.getModelType()).build());
        }

        if(Strings.isNotBlank(req.getTapControl1()) || Strings.isNotBlank(req.getTapControl2())){
            records.add(DeviceControlRecord.builder()
                    .controlType(CommandEnum.COMMAND_8B.getValue())
                    .controlData(req.getTapControl1()+req.getTapControl2()).build());
        }

        int probeType1 = 0;
        int probeType2 = 0;
        for(String i : req.getProbeType1()){
            probeType1 += Integer.valueOf(i);
        }
        for(String i : req.getProbeType2()){
            probeType2 += Integer.valueOf(i);
        }
        byte[] b = {Byte.parseByte(String.valueOf(probeType1)),Byte.parseByte(String.valueOf(probeType2))};
        records.add(DeviceControlRecord.builder()
                .controlType(CommandEnum.COMMAND_8C.getValue())
                .controlData(TransformUtils.byteToHexString(b)).build());

        if(Objects.nonNull(req.getTempControl())) {
            records.add(DeviceControlRecord.builder()
                    .controlType(CommandEnum.COMMAND_8D.getValue())
                    .controlData(TemperatureDecoder.encode(req.getTempControl())).build());
        }

        resp.setRecords(records);
        ctx.channel().writeAndFlush(resp);
    }

    @Override
    public ControlDeviceReq queryControlInfo(QueryControlInfoReq req) throws CodeException {
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()), false);
        if(device == null){
            throw new CodeException(ResultCode.ERROR_DEVICE__EXIST);
        }
        if(Strings.isBlank(device.getControlData())){
            return new ControlDeviceReq();
        }
        return Json.fromJson(ControlDeviceReq.class, device.getControlData());
    }

}
