package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/6/24 15:46
 */
@Data
public class ControlDeviceReq {

    private String startTime;
    private String stableTime;
    private String downTime;
    private String endTime;
    private String deviceNum;
    private Double startTemp = 0.0;
    private Double stableTemp = 0.0;
    private Double downTemp = 0.0;
    private Double endTemp = 0.0;
}
