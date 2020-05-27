package com.github.managesystem.controller;

import com.github.managesystem.model.req.DeleteTaskReq;
import com.github.managesystem.model.req.EditTaskReq;
import com.github.managesystem.model.req.ListTaskReq;
import com.github.managesystem.model.req.SimulationDataReq;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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









    @PostMapping(value = "/device/list")
    public Result listTaskDevice(@RequestBody ListTaskReq req){
       return Result.ok(Arrays.asList("123","234","345","789","678","567"));
    }

}