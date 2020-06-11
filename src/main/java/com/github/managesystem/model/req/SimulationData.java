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
    private Double startTemp = 0.0;
    private Double stableTemp = 0.0;
    private Double downTemp = 0.0;
    private Double endTemp = 0.0;
    private Integer randomData = 1;
    private Boolean effective;

}
