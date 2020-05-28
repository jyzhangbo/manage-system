package com.github.managesystem.model.req;

import com.github.managesystem.model.resp.AttributeInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 14:44
 */
@Data
public class EditDeviceUserReq {

    private String deviceNum;
    private String deviceName;
    private String img;
    private Integer collectSpace;

    private List<AttributeInfo> attributeInfo;

}
