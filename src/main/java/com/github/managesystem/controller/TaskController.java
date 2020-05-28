package com.github.managesystem.controller;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.AddTaskReq;
import com.github.managesystem.model.req.DeleteTaskReq;
import com.github.managesystem.model.req.EditTaskReq;
import com.github.managesystem.model.req.ListTaskReq;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 17:28
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @PostMapping(value = "/list")
    public Result listTask(@RequestBody ListTaskReq req){
        return Result.ok(taskService.listTask(req));
    }

    @PostMapping(value = "/delete")
    public Result deleteTask(@RequestBody DeleteTaskReq req){
        taskService.deleteTask(req);
        return Result.ok();
    }

    @PostMapping(value = "/edit")
    public Result editTask(@RequestBody EditTaskReq req){
        taskService.editTask(req);
        return Result.ok();
    }

    @PostMapping(value = "/add")
    public Result addTask(@RequestBody AddTaskReq req) throws CodeException {
        taskService.addTask(req);
        return Result.ok();
    }

}
