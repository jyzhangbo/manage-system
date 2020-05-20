package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 16:13
 */
@Data
public class ListAlarmReq {

    private String alarmObject;
    private String alarmTime;

}
