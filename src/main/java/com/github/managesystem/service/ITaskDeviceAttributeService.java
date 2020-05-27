package com.github.managesystem.service;

import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDeviceAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.resp.DeviceInfo;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface ITaskDeviceAttributeService extends IService<TaskDeviceAttribute> {


    List<DeviceInfo> listDeviceByTaskNum(String taskNum);

    void addTaskDeviceAttribute(Task task, List<String> devices);
}
