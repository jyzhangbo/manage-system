package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.AlarmRule;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.AlarmRuleMapper;
import com.github.managesystem.model.constant.JudgeTypeEnum;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.req.EnableAlarmRuleReq;
import com.github.managesystem.model.req.ListAlarmRuleReq;
import com.github.managesystem.model.req.UpdateAlarmRuleReq;
import com.github.managesystem.model.resp.ListAlarmRuleResp;
import com.github.managesystem.service.IAlarmRuleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
@Service
public class AlarmRuleServiceImpl extends ServiceImpl<AlarmRuleMapper, AlarmRule> implements IAlarmRuleService {

    @Override
    public ListAlarmRuleResp listRule(ListAlarmRuleReq req, HttpServletRequest request){
        ListAlarmRuleResp resp = new ListAlarmRuleResp();

        QueryWrapper<AlarmRule> queryWrapper = new QueryWrapper<>();
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Device.COMPANY_NAME,user.getCompanyName());
        }

        queryWrapper.eq(AlarmRule.IS_DEL,0);
        List<AlarmRule> alarmRuleList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(alarmRuleList)){
            alarmRuleList = buildAlarmRules(user);
            this.saveBatch(alarmRuleList);
        }
        resp.setInfos(alarmRuleList);
        return resp;
    }

    @Override
    public void enableRule(EnableAlarmRuleReq req, HttpServletRequest request){
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        AlarmRule alarmRule = new AlarmRule();
        alarmRule.setId(req.getId());
        alarmRule.setIsEnable((req.getIsEnable()+1)%2);
        alarmRule.setModifer(user.getLoginName());
        alarmRule.setModifyTime(LocalDateTime.now());
        this.updateById(alarmRule);
    }

    @Override
    public void updateRule(UpdateAlarmRuleReq req, HttpServletRequest request){
        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        AlarmRule alarmRule = new AlarmRule();
        alarmRule.setId(req.getId());
        alarmRule.setJudgeValue(req.getJudgeValue());
        alarmRule.setModifer(user.getLoginName());
        alarmRule.setModifyTime(LocalDateTime.now());
        this.updateById(alarmRule);
    }

    private List<AlarmRule> buildAlarmRules(User user){
        LocalDateTime now = LocalDateTime.now();
        AlarmRule alarmRule1 = AlarmRule.builder().alarmRuleObject("温度上限").alarmRuleName("温度")
                .judgeType(JudgeTypeEnum.JUDGE_TYPE_4.getDesc()).judgeValue(60).judgeUnit("℃")
                .isEnable(1)
                .companyName(user.getCompanyName()).createTime(now).creater(user.getLoginName()).build();
        AlarmRule alarmRule2 = AlarmRule.builder().alarmRuleObject("温度下限").alarmRuleName("温度")
                .judgeType(JudgeTypeEnum.JUDGE_TYPE_5.getDesc()).judgeValue(30).judgeUnit("℃")
                .isEnable(1)
                .companyName(user.getCompanyName()).createTime(now).creater(user.getLoginName()).build();
        List<AlarmRule> list = new ArrayList<>();
        list.add(alarmRule1);
        list.add(alarmRule2);
        return list;
    }
}
