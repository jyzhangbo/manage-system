package com.github.managesystem.model.req;

import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/30 20:09
 */
@Data
public class CopyDataReq {

    private String taskNum;
    private String deviceNum;
    private String fromAttr;
    private List<String> toAttr;
    private Double addData;
    private Double randomData;

}
