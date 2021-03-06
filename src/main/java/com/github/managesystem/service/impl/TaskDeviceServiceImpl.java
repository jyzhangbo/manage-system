package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.mapper.TaskDeviceMapper;
import com.github.managesystem.model.constant.DeviceStateEnum;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.constant.TaskStateEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.ListTaskSearchReq;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.service.ITaskDeviceService;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public List<DeviceInfo> listDeviceByTaskNum(String taskNum,String companyName) {
        List<TaskDevice> list = this.list(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, taskNum).eq(TaskDevice.COMPANY_NAME, companyName));
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

            taskDevices.add(TaskDevice.builder().attributeInfo(device.getAttributeInfo())
                    .createTime(LocalDateTime.now())
                    .modifyTime(LocalDateTime.now())
                    .deviceImg(device.getDeviceImg())
                    .deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum())
                    .taskNum(task.getTaskNum())
                    .taskName(task.getTaskName())
                    .companyName(task.getCompanyName())
                    .collectSpace(device.getCollectSpace())
                    .taskStatus(task.getTaskStatus())
                    .build());
        }
        this.saveBatch(taskDevices);

    }

    @Override
    public TaskDevice asertTaskDevice(String taskNum,String deviceNum) throws CodeException {
        List<TaskDevice> taskDevices = this.list(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, taskNum)
                .eq(Strings.isNotBlank(deviceNum), TaskDevice.DEVICE_NUM, deviceNum));
        if(taskDevices.size() == 0){
            throw new CodeException(ResultCode.ERROR_TASK_DEVICE_NULL);
        }
        return taskDevices.get(0);
    }

    @Override
    public List<TaskDevice> listTaskDeviceBySearch(ListTaskSearchReq req, HttpServletRequest request) {

        QueryWrapper<TaskDevice> queryWrapper = new QueryWrapper<>();
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }
        queryWrapper.eq(TaskDevice.TASK_STATUS,TaskStateEnum.START.value);
        return this.list(queryWrapper.like(TaskDevice.DEVICE_NAME, req.getParam()).or().like(TaskDevice.TASK_NAME, req.getParam()));
    }

}
