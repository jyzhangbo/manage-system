package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.mapper.TaskDeviceMapper;
import com.github.managesystem.model.constant.DeviceStateEnum;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.service.IDeviceService;
import com.github.managesystem.service.ITaskDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class TaskDeviceServiceImpl extends ServiceImpl<TaskDeviceMapper, TaskDevice> implements ITaskDeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public List<DeviceInfo> listDeviceByTaskNum(String taskNum) {
        List<TaskDevice> list = this.list(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, taskNum));
        List<DeviceInfo> devices = new ArrayList();
        for (TaskDevice attribute : list) {
            devices.add(DeviceInfo.builder().deviceNum(attribute.getDeviceNum())
                    .deviceName(attribute.getDeviceName())
                    .img(attribute.getDeviceImg()).build());
        }
        return devices;
    }

    @Override
    public void addTaskDevice(Task task, List<String> devices) {
        List<TaskDevice> taskDevices = new ArrayList<>();
        for (String deviceNum : devices) {
            Device device = deviceMapper.selectOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, deviceNum));
            device.setDeviceState(DeviceStateEnum.USING.value);
            device.setModifyTime(LocalDateTime.now());
            deviceMapper.updateById(device);

            taskDevices.add(TaskDevice.builder().attributeInfo(device.getAttributeInfo())
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
