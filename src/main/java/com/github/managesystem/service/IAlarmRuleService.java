package com.github.managesystem.service;

import com.github.managesystem.entity.AlarmRule;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.req.EnableAlarmRuleReq;
import com.github.managesystem.model.req.ListAlarmRuleReq;
import com.github.managesystem.model.req.UpdateAlarmRuleReq;
import com.github.managesystem.model.resp.ListAlarmRuleResp;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
public interface IAlarmRuleService extends IService<AlarmRule> {

    ListAlarmRuleResp listRule(ListAlarmRuleReq req, HttpServletRequest request);

    void enableRule(EnableAlarmRuleReq req);

    void updateRule(UpdateAlarmRuleReq req);
}
