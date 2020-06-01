package com.github.managesystem.controller;

import com.github.managesystem.model.req.EnableAlarmRuleReq;
import com.github.managesystem.model.req.ListAlarmRuleReq;
import com.github.managesystem.model.req.UpdateAlarmRuleReq;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.IAlarmRuleService;
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
@RequestMapping("/alarm/rule")
public class AlarmRuleController {

    @Autowired
    private IAlarmRuleService alarmRuleService;

    @PostMapping(value = "/list")
    public Result listAlarmRule(@RequestBody ListAlarmRuleReq req){
        return Result.ok(alarmRuleService.listRule(req));
    }

    @PostMapping(value = "/enable")
    public Result enableAlarmRule(@RequestBody EnableAlarmRuleReq req){
        alarmRuleService.enableRule(req);
        return Result.ok();
    }

    @PostMapping(value = "/update")
    public Result updateAlarmRule(@RequestBody UpdateAlarmRuleReq req){
        alarmRuleService.updateRule(req);
        return Result.ok();
    }

}
