package com.github.managesystem.service;

import com.github.managesystem.entity.AlarmLog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.resp.ListAlarmResp;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
public interface IAlarmLogService extends IService<AlarmLog> {

    ListAlarmResp listLog(ListAlarmReq req, HttpServletRequest request);
}
