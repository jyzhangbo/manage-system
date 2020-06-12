package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.AlarmLog;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.AlarmLogMapper;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.resp.ListAlarmResp;
import com.github.managesystem.service.IAlarmLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TimeUtils;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
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
    public ListAlarmResp listLog(ListAlarmReq req, HttpServletRequest request){
        ListAlarmResp resp = new ListAlarmResp();

        QueryWrapper<AlarmLog> queryWrapper = new QueryWrapper<>();
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        Page<AlarmLog> page = new Page<>(req.getPageNumber(),req.getPageSize());

        queryWrapper.eq(AlarmLog.IS_DEL,0);
        if (!StringUtils.isEmpty(req.getAlarmObject())){
            queryWrapper.like(AlarmLog.ALARM_RULE_OBJECT, req.getAlarmObject());
        }
        if (!StringUtils.isEmpty(req.getAlarmStartTime())){
            LocalDateTime alarmTime = TimeUtils.parseTime(req.getAlarmStartTime());
            queryWrapper.ge(AlarmLog.CREATE_TIME, alarmTime);
        }
        if (!StringUtils.isEmpty(req.getAlarmEndTime())){
            LocalDateTime alarmTime = TimeUtils.parseTime(req.getAlarmEndTime());
            queryWrapper.le(AlarmLog.CREATE_TIME, alarmTime);
        }
        IPage<AlarmLog> iPage = this.page(page,queryWrapper);
        resp.setTotal(iPage.getTotal());
        resp.setInfos(iPage.getRecords());
        return resp;
    }
}
