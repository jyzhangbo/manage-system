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
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.*;

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
    public ListDeviceUserResp listDeviceUser(ListDeviceUserReq req, HttpServletRequest request) {
        ListDeviceUserResp resp = new ListDeviceUserResp();
        Page<Device> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();

        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        if(Strings.isNotBlank(req.getDeviceName())){
            queryWrapper.eq(Device.DEVICE_NAME,req.getDeviceName());
        }
        if(Strings.isNotBlank(req.getDeviceNum())){
            queryWrapper.eq(Device.DEVICE_NUM,req.getDeviceNum());
        }

        IPage<Device> devices = this.page(page, queryWrapper);

        for(Device device : devices.getRecords()){
            Map<String, String> attribute = Json.fromJsonAsMap(String.class, device.getAttributeInfo());
            List<AttributeInfo> infos = new ArrayList<>();
            for(Map.Entry<String,String> entry : attribute.entrySet()){
                infos.add(AttributeInfo.builder().code(entry.getKey()).name(entry.getValue()).build());
            }
            resp.getInfos().add(ListDeviceUserInfo.builder()
                    .deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum())
                    .img(device.getDeviceImg())
                    .collectSpace(device.getCollectSpace())
                    .attributeInfo(infos)
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
    public void editDeviceAdmin(EditDeviceAdmin req) {
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()));
        device.setCompanyName(req.getCompanyName());
        device.setModifyTime(LocalDateTime.now());
        this.updateById(device);
    }

    @Override
    public void addDevice(AddDeviceReq req) {
        Map<String,String> attributeInfo = new LinkedHashMap<>();
        attributeInfo.put("T1","T1");
        attributeInfo.put("T2","T2");
        attributeInfo.put("T3","T3");
        attributeInfo.put("T4","T4");
        attributeInfo.put("T5","T5");
        attributeInfo.put("T6","T6");
        attributeInfo.put("T7","T7");
        attributeInfo.put("T8","T8");

        Device device = Device.builder().deviceNum(req.getDeviceNum())
                .deviceName(req.getDeviceNum())
                .companyName(req.getCompanyName())
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .collectSpace(30 * 60)
                .deviceImg("")
                .deviceState(DeviceStateEnum.UNUSE.value)
                .attributeInfo(Json.toJson(attributeInfo,JsonFormat.tidy()))
                .build();

        this.save(device);
    }

    @Override
    public void editDeviceUser(EditDeviceUserReq req, HttpServletRequest request) {
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()),false);
        device.setDeviceName(req.getDeviceName());
        device.setCollectSpace(req.getCollectSpace());
        device.setDeviceImg(req.getImg());

        Map<String,String> attributeInfo = new LinkedHashMap<>();
        for(AttributeInfo info : req.getAttributeInfo()){
            attributeInfo.put(info.getCode(),info.getName());
        }
        device.setAttributeInfo(Json.toJson(attributeInfo,JsonFormat.tidy()));
        device.setModifyTime(LocalDateTime.now());
        this.updateById(device);

        QueryWrapper<Task> queryWrapper = new QueryWrapper<Task>().ne(Task.TASK_STATUS, 2);
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        List<Task> tasks = taskMapper.selectList(queryWrapper);

        TaskDevice taskDevice = TaskDevice.builder()
                .deviceImg(device.getDeviceImg())
                .deviceName(device.getDeviceName())
                .attributeInfo(device.getAttributeInfo())
                .modifyTime(LocalDateTime.now()).build();
        for(Task task : tasks){
            taskDeviceMapper.update(taskDevice,new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM,task.getTaskNum())
                            .eq(TaskDevice.DEVICE_NUM,device.getDeviceNum()));
        }

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
