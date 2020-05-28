package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 14:35
 */
@Data
@Builder
public class ListDeviceUserInfo {

    private String deviceNum;
    private String deviceName;
    private String img;
    private Integer collectSpace;

    private List<AttributeInfo> attributeInfo;

}
