package com.github.managesystem.model.req;

import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 14:52
 */
@Data
public class SimulationDataReq {

    private String startTime;
    private String stableTime;
    private String downTime;
    private String endTime;
    private String taskNum;
    private String deviceNum;
    private Integer timeSpace;
    private Integer randomTime;
    private List<SimulationData> listTemp;

}
