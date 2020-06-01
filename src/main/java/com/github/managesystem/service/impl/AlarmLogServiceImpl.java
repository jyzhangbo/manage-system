package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.AlarmLog;
import com.github.managesystem.mapper.AlarmLogMapper;
import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.resp.ListAlarmResp;
import com.github.managesystem.service.IAlarmLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
@Service
public class AlarmLogServiceImpl extends ServiceImpl<AlarmLogMapper, AlarmLog> implements IAlarmLogService {

    @Override
    public ListAlarmResp listLog(ListAlarmReq req){
        ListAlarmResp resp = new ListAlarmResp();
        if (StringUtils.isEmpty(req.getCompanyName())){
            return resp;
        }
        Page<AlarmLog> page = new Page<>(req.getPageNumber(),req.getPageSize());
        QueryWrapper<AlarmLog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(AlarmLog.COMPANY_NAME, req.getCompanyName());
        queryWrapper.eq(AlarmLog.IS_DEL,0);
        if (!StringUtils.isEmpty(req.getAlarmObject())){
            queryWrapper.like(AlarmLog.ALARM_RULE_OBJECT, req.getAlarmObject());
        }
        if (!StringUtils.isEmpty(req.getAlarmStartTime())){
            LocalDateTime alarmTime = LocalDateTime.parse(req.getAlarmStartTime(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            queryWrapper.ge(AlarmLog.CREATE_TIME, alarmTime);
        }
        if (!StringUtils.isEmpty(req.getAlarmEndTime())){
            LocalDateTime alarmTime = LocalDateTime.parse(req.getAlarmEndTime(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            queryWrapper.le(AlarmLog.CREATE_TIME, alarmTime);
        }
        IPage<AlarmLog> iPage = this.page(page,queryWrapper);
        resp.setTotal(iPage.getTotal());
        resp.setInfos(iPage.getRecords());
        return resp;
    }
}
