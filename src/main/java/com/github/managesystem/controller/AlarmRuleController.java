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

import javax.servlet.http.HttpServletRequest;

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
    public Result listAlarmRule(@RequestBody ListAlarmRuleReq req,HttpServletRequest request){
        return Result.ok(alarmRuleService.listRule(req,request));
    }

    @PostMapping(value = "/enable")
    public Result enableAlarmRule(@RequestBody EnableAlarmRuleReq req,HttpServletRequest request){
        alarmRuleService.enableRule(req,request);
        return Result.ok();
    }

    @PostMapping(value = "/update")
    public Result updateAlarmRule(@RequestBody UpdateAlarmRuleReq req,HttpServletRequest request){
        alarmRuleService.updateRule(req,request);
        return Result.ok();
    }

}
