package com.github.managesystem.model.req;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 16:13
 */
@Data
public class ListAlarmReq {

    private String alarmObject;
    private String alarmStartTime;
    private String alarmEndTime;
    private Integer pageNumber;
    private Integer pageSize;

}
