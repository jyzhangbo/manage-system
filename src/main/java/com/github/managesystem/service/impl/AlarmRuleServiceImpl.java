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
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            alarmRuleList = buildAlarmRules(user.getCompanyName());
            this.saveBatch(alarmRuleList);
        }
        List<AlarmRule> respList = alarmRuleList.stream()
                .filter(x -> (StringUtils.isEmpty(req.getAlarmObject()) || x.getAlarmRuleObject().contains(req.getAlarmObject())) &&
                        (Objects.isNull(req.getIsEnable()) || req.getIsEnable() == x.getIsEnable().intValue()))
                .collect(Collectors.toList());
        resp.setInfos(respList);
        resp.setTotal(respList.size());
        return resp;
    }

    @Override
    public void enableRule(EnableAlarmRuleReq req){
        AlarmRule alarmRule = new AlarmRule();
        alarmRule.setId(req.getId());
        alarmRule.setIsEnable((req.getIsEnable()+1)%2);
        alarmRule.setModifer(req.getModifer());
        alarmRule.setModifyTime(LocalDateTime.now());
        this.updateById(alarmRule);
    }

    @Override
    public void updateRule(UpdateAlarmRuleReq req){
        AlarmRule alarmRule = new AlarmRule();
        alarmRule.setId(req.getId());
        alarmRule.setJudgeValue(req.getJudgeValue());
        alarmRule.setModifer(req.getModifer());
        alarmRule.setModifyTime(LocalDateTime.now());
        this.updateById(alarmRule);
    }

    private List<AlarmRule> buildAlarmRules(String companyName){
        LocalDateTime now = LocalDateTime.now();
        AlarmRule alarmRule1 = AlarmRule.builder().alarmRuleObject("温度上限").alarmRuleName("温度")
                .judgeType(JudgeTypeEnum.JUDGE_TYPE_4.getDesc()).judgeValue(60).judgeUnit("℃")
                .companyName(companyName).createTime(now).build();
        AlarmRule alarmRule2 = AlarmRule.builder().alarmRuleObject("温度下限").alarmRuleName("温度")
                .judgeType(JudgeTypeEnum.JUDGE_TYPE_5.getDesc()).judgeValue(30).judgeUnit("℃")
                .companyName(companyName).createTime(now).build();
        List<AlarmRule> list = new ArrayList<>();
        list.add(alarmRule1);
        list.add(alarmRule2);
        return list;
    }
}
