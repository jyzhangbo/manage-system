package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.*;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.mapper.TaskMapper;
import com.github.managesystem.model.constant.DeviceStateEnum;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.constant.TaskStateEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.model.resp.ListTaskInfo;
import com.github.managesystem.model.resp.ListTaskResp;
import com.github.managesystem.service.IDeviceDataService;
import com.github.managesystem.service.ITaskDeviceService;
import com.github.managesystem.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TimeUtils;
import com.sun.javafx.tk.Toolkit;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private IDeviceDataService deviceDataService;


    @Override
    public ListTaskResp listTask(ListTaskReq req, HttpServletRequest request) {
        ListTaskResp resp = new ListTaskResp();
        Page<Task> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();

        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        if(Strings.isNotBlank(req.getTaskName())){
            queryWrapper.eq(Task.TASK_NAME,req.getTaskName());
        }
        if(Strings.isNotBlank(req.getTaskNum())){
            queryWrapper.eq(Task.TASK_NUM,req.getTaskNum());
        }
        if(Objects.nonNull(req.getTaskState()) && !Objects.equals(req.getTaskState(),TaskStateEnum.ALL.value)){
            queryWrapper.eq(Task.TASK_STATUS,req.getTaskState());
        } else if(Objects.isNull(req.getTaskState())) {
            queryWrapper.eq(Task.TASK_STATUS,TaskStateEnum.START.value);
        }

        IPage<Task> page1 = this.page(page, queryWrapper.orderByDesc(Task.MODIFY_TIME));

        for(Task task : page1.getRecords()){
            List<DeviceInfo> devices = taskDeviceService.listDeviceByTaskNum(task.getTaskNum(), task.getCompanyName());
            resp.getTasks().add(ListTaskInfo.builder().companyName(task.getCompanyName())
                    .taskName(task.getTaskName())
                    .taskNum(task.getTaskNum())
                    .endTime(TimeUtils.formatTime(task.getEndTime()))
                    .startTime(TimeUtils.formatTime(task.getStartTime()))
                    .taskStatus(task.getTaskStatus())
                    .devices(devices)
                    .build());
        }
        resp.setTotal(page1.getTotal());
        return resp;
    }

    @Override
    public void deleteTask(DeleteTaskReq req) {
        this.remove(new QueryWrapper<Task>().eq(Task.TASK_NUM,req.getTaskNum()).eq(Task.COMPANY_NAME,req.getCompanyName()));
        taskDeviceService.remove(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM,req.getTaskNum()).eq(Task.COMPANY_NAME,req.getCompanyName()));
        deviceDataService.remove(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM,req.getTaskNum()).eq(Task.COMPANY_NAME,req.getCompanyName()));
    }

    @Override
    public void editTask(EditTaskReq req) {
        Task task = this.getOne(new QueryWrapper<Task>().eq(Task.TASK_NUM, req.getTaskNum()).eq(Task.COMPANY_NAME, req.getCompanyName()),false);
        if(Objects.nonNull(req.getState())) {
            task.setTaskStatus(req.getState());
            task.setModifyTime(LocalDateTime.now());
            if (Objects.equals(req.getState(), TaskStateEnum.START.value)) {
                task.setStartTime(LocalDateTime.now());
            }else if (Objects.equals(req.getState(), TaskStateEnum.END.value)) {
                task.setEndTime(LocalDateTime.now());
            }
            this.updateById(task);
            taskDeviceService.update(new UpdateWrapper<TaskDevice>()
                    .set(TaskDevice.MODIFY_TIME,LocalDateTime.now())
                    .set(TaskDevice.TASK_STATUS,req.getState())
                    .eq(TaskDevice.TASK_NUM,req.getTaskNum())
                    .eq(TaskDevice.COMPANY_NAME,req.getCompanyName())
                    .in(TaskDevice.DEVICE_NUM,req.getDevices()));

        }else {
            task.setTaskName(req.getTaskName());
            task.setModifyTime(LocalDateTime.now());
            this.updateById(task);
            taskDeviceService.remove(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, req.getTaskNum()).eq(TaskDevice.COMPANY_NAME, req.getCompanyName()));
            if (req.getDevices().size() > 0) {
                taskDeviceService.addTaskDevice(task, req.getDevices());
            }
        }

    }

    @Override
    public void addTask(AddTaskReq req, HttpServletRequest request) throws CodeException{
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);

        Task task = this.getOne(new QueryWrapper<Task>().eq(Task.TASK_NUM, req.getTaskNum()).eq(Task.COMPANY_NAME, user.getCompanyName()), false);
        if(Objects.nonNull(task)){
            throw new CodeException(ResultCode.ERROR_TASK);
        }


        task = Task.builder().taskName(req.getTaskName())
                .taskNum(req.getTaskNum())
                .taskStatus(TaskStateEnum.CREATE.value)
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .companyName(user.getCompanyName())
                .build();
        this.save(task);

        taskDeviceService.addTaskDevice(task,req.getDevices());

    }

    @Override
    public List<TaskDevice> listTaskSearch(ListTaskSearchReq req, HttpServletRequest request) {

        return taskDeviceService.listTaskDeviceBySearch(req,request);
    }
}
