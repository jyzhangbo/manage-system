package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 15:28
 */
@Data
public class ListAlarmRuleReq {

    private String alarmObject;
    private Integer isEnable;

}
