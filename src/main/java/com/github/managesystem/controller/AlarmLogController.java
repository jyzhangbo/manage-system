package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.IAlarmLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
@RestController
@RequestMapping("/alarm/log")
public class AlarmLogController {

    @Autowired
    private IAlarmLogService alarmLogService;

    @PostMapping(value = "/list")
    public Result listAlarmLog(@RequestBody ListAlarmReq req){
        return Result.ok(alarmLogService.listLog(req));
    }
}
