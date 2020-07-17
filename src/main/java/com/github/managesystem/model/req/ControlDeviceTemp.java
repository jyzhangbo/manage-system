package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/7/17 14:49
 */
@Data
public class ControlDeviceTemp {

    private Double startTime;
    private Double startTemp;
    private Double upSpeed;
    private Double constantTemp;
    private Double constantTime;
    private Double downSpeed;
    private Double endTemp;

}
