package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.req.ListAlarmRuleReq;
import com.github.managesystem.model.resp.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 15:27
 */
@RestController
@RequestMapping("/alarm")
public class AlarmController {

    @PostMapping(value = "/rule/list")
    public Result listAlarmRule(@RequestBody ListAlarmRuleReq req){
        List<ListAlarmRuleInfo> datas = new ArrayList<>();
        datas.add(ListAlarmRuleInfo.builder().alarmObject("温度上限").alarmRule("60").createTime("2020-05-18").state(1).build());
        datas.add(ListAlarmRuleInfo.builder().alarmObject("温度下限").alarmRule("10").createTime("2020-05-18").state(0).build());
        return Result.ok(ListAlarmRuleResp.builder().infos(datas).total(datas.size()).build());
    }

    @PostMapping(value = "/list")
    public Result listAlarm(@RequestBody ListAlarmReq req){
        List<ListAlarmInfo> datas = new ArrayList<>();
        datas.add(ListAlarmInfo.builder().alarmObject("温度上限").alarmRule("60").alarmTime("2020-05-20").deviceNum("123").collectId("t1").build());
        datas.add(ListAlarmInfo.builder().alarmObject("温度下限").alarmRule("10").alarmTime("2020-05-18").deviceNum("456").collectId("T2").build());
        return Result.ok(ListAlarmResp.builder().infos(datas).total(datas.size()).build());
    }

}
