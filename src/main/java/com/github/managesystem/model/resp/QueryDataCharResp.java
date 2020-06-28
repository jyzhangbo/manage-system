package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 14:53
 */
@Data
@Builder
public class QueryDataCharResp {

    public List<String> xdatas;

    public List<ChartYData> ydatas;

    private Map<String,String> tableHeader;

    private String deviceImg;

    private Integer collectSpace;

    private List<AttributeInfo> attributeInfo;

    private String deviceName;

}
