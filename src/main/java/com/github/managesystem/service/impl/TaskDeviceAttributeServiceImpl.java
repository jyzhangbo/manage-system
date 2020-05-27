package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDeviceAttribute;
import com.github.managesystem.mapper.TaskDeviceAttributeMapper;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.service.IDeviceService;
import com.github.managesystem.service.ITaskDeviceAttributeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class TaskDeviceAttributeServiceImpl extends ServiceImpl<TaskDeviceAttributeMapper, TaskDeviceAttribute> implements ITaskDeviceAttributeService {

    @Autowired
    private IDeviceService deviceService;

    @Override
    public List<DeviceInfo> listDeviceByTaskNum(String taskNum) {
        List<TaskDeviceAttribute> list = this.list(new QueryWrapper<TaskDeviceAttribute>().eq(TaskDeviceAttribute.TASK_NUM, taskNum));
        List<DeviceInfo> devices = new ArrayList();
        for(TaskDeviceAttribute attribute : list){
            devices.add(DeviceInfo.builder().deviceNum(attribute.getDeviceNum())
                    .deviceName(attribute.getDeviceName())
                    .img(attribute.getDeviceImg()).build());
        }
        return devices;
    }

    @Override
    public void addTaskDeviceAttribute(Task task, List<String> devices) {
        List<TaskDeviceAttribute> taskDevices= new ArrayList<>();
        for(String deviceNum : devices){
            Device device = deviceService.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, deviceNum));
            taskDevices.add(TaskDeviceAttribute.builder().attributeInfo(device.getAttributeInfo())
                    .createTime(LocalDateTime.now())
                    .modifyTime(LocalDateTime.now())
                    .deviceImg(device.getDeviceImg())
                    .deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum())
                    .taskNum(task.getTaskNum())
                    .taskName(task.getTaskName())
                    .build());
        }
        this.saveBatch(taskDevices);

    }
}
