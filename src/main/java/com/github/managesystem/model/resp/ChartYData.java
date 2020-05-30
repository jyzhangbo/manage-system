package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 15:28
 */
@Data
@Builder
public class ChartYData {

    private String name;

    private List<Double> values;

}
