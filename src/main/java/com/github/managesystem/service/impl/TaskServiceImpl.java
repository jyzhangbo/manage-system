package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDeviceAttribute;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.TaskMapper;
import com.github.managesystem.model.req.DeleteTaskReq;
import com.github.managesystem.model.req.EditTaskReq;
import com.github.managesystem.model.req.ListTaskReq;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.model.resp.ListTaskInfo;
import com.github.managesystem.model.resp.ListTaskResp;
import com.github.managesystem.service.ITaskDeviceAttributeService;
import com.github.managesystem.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TimeUtils;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;

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

    private ITaskDeviceAttributeService taskDeviceAttributeService;

    @Override
    public ListTaskResp listTask(ListTaskReq req) {
        ListTaskResp resp = new ListTaskResp();
        Page<Task> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(req.getTaskName())){
            queryWrapper.eq(Task.TASK_NAME,req.getTaskName());
        }
        if(Strings.isNotBlank(req.getTaskNum())){
            queryWrapper.eq(Task.TASK_NUM,req.getTaskNum());
        }


        IPage<Task> page1 = this.page(page, queryWrapper);

        for(Task task : page1.getRecords()){
            List<DeviceInfo> devices = taskDeviceAttributeService.listDeviceByTaskNum(task.getTaskNum());
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
        taskDeviceAttributeService.remove(new QueryWrapper<TaskDeviceAttribute>().eq(TaskDeviceAttribute.TASK_NUM,req.getTaskNum()));
    }

    @Override
    public void editTask(EditTaskReq req) {
        Task task = this.getOne(new QueryWrapper<Task>().eq(Task.TASK_NUM, req.getTaskNum()));
        if(Objects.nonNull(req.getState())){
            task.setTaskStatus(req.getState());
            this.updateById(task);
            return;
        }
        if(req.getDevices().size() > 0){
            taskDeviceAttributeService.remove(new QueryWrapper<TaskDeviceAttribute>().eq(TaskDeviceAttribute.TASK_NUM,req.getTaskNum()));
            taskDeviceAttributeService.addTaskDeviceAttribute(task,req.getDevices());
        }


    }
}
