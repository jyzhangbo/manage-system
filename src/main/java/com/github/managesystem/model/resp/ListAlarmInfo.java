package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 15:30
 */
@Data
@Builder
public class ListAlarmInfo {

    private String alarmObject;
    private String alarmRule;
    private String alarmTime;
    private String deviceNum;
    private String collectId;

}
