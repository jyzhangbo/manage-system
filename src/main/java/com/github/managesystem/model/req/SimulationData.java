package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/30 17:23
 */
@Data
public class SimulationData {

    private String name;
    private String code;
    private Double startTemp;
    private Double stableTemp;
    private Double downTemp;
    private Double endTemp;
    private Integer randomData;
    private Boolean effective;

}
