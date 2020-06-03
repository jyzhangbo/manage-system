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
import com.github.managesystem.service.ITaskDeviceService;
import com.github.managesystem.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TimeUtils;
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
    private DeviceMapper deviceMapper;

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
        if(Objects.nonNull(req.getTaskState())){
            queryWrapper.eq(Task.TASK_STATUS,req.getTaskState());
        }

        IPage<Task> page1 = this.page(page, queryWrapper.orderByDesc(Task.MODIFY_TIME));

        for(Task task : page1.getRecords()){
            List<DeviceInfo> devices = taskDeviceService.listDeviceByTaskNum(task.getTaskNum());
            resp.getTasks().add(ListTaskInfo.builder().taskName(task.getTaskName())
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
        this.remove(new QueryWrapper<Task>().eq(Task.TASK_NUM,req.getTaskNum()));
        taskDeviceService.remove(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM,req.getTaskNum()));
    }

    @Override
    public void editTask(EditTaskReq req) {
        Task task = this.getOne(new QueryWrapper<Task>().eq(Task.TASK_NUM, req.getTaskNum()),false);
        if(Objects.nonNull(req.getState())) {
            task.setTaskStatus(req.getState());
            task.setModifyTime(LocalDateTime.now());
            if (Objects.equals(req.getState(), TaskStateEnum.START.value)) {
                task.setStartTime(LocalDateTime.now());
            }else if (Objects.equals(req.getState(), TaskStateEnum.END.value)) {
                task.setEndTime(LocalDateTime.now());
                Device device = Device.builder()
                        .deviceState(DeviceStateEnum.UNUSE.value)
                        .modifyTime(LocalDateTime.now())
                        .build();
                deviceMapper.update(device, new QueryWrapper<Device>().in(Device.DEVICE_NUM, req.getDevices()));
            }

            this.updateById(task);
            return;
        }

        if(req.getDevices().size() > 0){
            taskDeviceService.remove(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM,req.getTaskNum()));
            taskDeviceService.addTaskDevice(task,req.getDevices());
        }


    }

    @Override
    public void addTask(AddTaskReq req, HttpServletRequest request) throws CodeException{
        Task task = this.getOne(new QueryWrapper<Task>().eq(Task.TASK_NUM, req.getTaskNum()), false);
        if(Objects.nonNull(task)){
            throw new CodeException(ResultCode.ERROR_TASK);
        }

        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);

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


    public String asertTaskNum(String taskNum, HttpServletRequest request) throws CodeException{
        if(Strings.isBlank(taskNum)) {

            QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
            User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
            if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
                queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
            }

            List<Task> tasks = this.list(queryWrapper.orderByDesc(Task.MODIFY_TIME));
            if(tasks.size() == 0){
                throw new CodeException(ResultCode.ERROR_TASK_NULL);
            }
            return tasks.get(0).getTaskNum();
        }
        return taskNum;
    }

    @Override
    public List<TaskDevice> listTaskSearch(ListTaskSearchReq req, HttpServletRequest request) {

        return taskDeviceService.listTaskDeviceBySearch(req,request);
    }
}
