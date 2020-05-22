package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 14:53
 */
@Data
@Builder
public class SimulationDataResp {

    public List<String> xDatas;

    public List<SimulationData> yDatas;

}
