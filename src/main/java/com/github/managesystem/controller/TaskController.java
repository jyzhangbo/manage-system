package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListTaskReq;
import com.github.managesystem.model.req.SimulationDataReq;
import com.github.managesystem.model.resp.*;
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

    @PostMapping(value = "/list")
    public Result listTask(@RequestBody ListTaskReq req){
        List<ListTaskInfo> tasks = new ArrayList<>();
        tasks.add(ListTaskInfo.builder().taskName("任务1").taskNum("22").state(1).startTime("2020-05-13").endTime("2020-05-20").devices(Arrays.asList("123","234")).build());
        tasks.add(ListTaskInfo.builder().taskName("任务2").taskNum("33").state(1).startTime("2020-05-15").endTime("2020-05-21").devices(Arrays.asList("789","678")).build());
        return Result.ok(ListTaskResp.builder().tasks(tasks).build());
    }

    @PostMapping(value = "/device/list")
    public Result listTaskDevice(@RequestBody ListTaskReq req){
       return Result.ok(Arrays.asList("123","234","345","789","678","567"));
    }

}
