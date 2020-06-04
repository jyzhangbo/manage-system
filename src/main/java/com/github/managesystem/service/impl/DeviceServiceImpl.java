package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.mapper.TaskDeviceMapper;
import com.github.managesystem.mapper.TaskMapper;
import com.github.managesystem.model.constant.AttributeEnum;
import com.github.managesystem.model.constant.DeviceStateEnum;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    public void addDevice(AddDeviceReq req) {
        Device device = Device.builder().deviceNum(req.getDeviceNum())
                .deviceName(req.getDeviceNum())
                .companyName(req.getCompanyName())
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .collectSpace(deviceCollectSpace)
                .deviceImg(deviceImg)
                .deviceState(DeviceStateEnum.UNUSE.value)
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
        List<ListDeviceTaskResp> resp = new ArrayList<>();

        QueryWrapper<Device> queryWrapper = new QueryWrapper<Device>().eq(Device.DEVICE_STATE, DeviceStateEnum.UNUSE.value);
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        List<Device> devices = this.list(queryWrapper);

        for(Device device : devices){
            resp.add(ListDeviceTaskResp.builder().deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum()).build());
        }
        if(Strings.isNotBlank(req.getTaskNum())){
            List<TaskDevice> taskDevices = taskDeviceMapper.selectList(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, req.getTaskNum()));
            for(TaskDevice taskDevice : taskDevices){
                resp.add(ListDeviceTaskResp.builder().deviceName(taskDevice.getDeviceName())
                        .deviceNum(taskDevice.getDeviceNum()).build());
            }
        }
        return resp;
    }

}
