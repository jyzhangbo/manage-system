package com.github.managesystem.service;

import com.github.managesystem.entity.Task;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.ListTaskSearchReq;
import com.github.managesystem.model.resp.DeviceInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface ITaskDeviceService extends IService<TaskDevice> {


    List<DeviceInfo> listDeviceByTaskNum(String taskNum);

    void addTaskDevice(Task task, List<String> devices);

    TaskDevice asertTaskDevice(String taskNum,String deviceNum) throws CodeException;

    List<TaskDevice> listTaskDeviceBySearch(ListTaskSearchReq req, HttpServletRequest request);
}
