package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 9:48
 */
@Data
@Builder
public class ListDeviceInfo {

    private String companyName;
    private String phone;
    private String loginName;
    private String deviceNum;
    private List<AttributeInfo> devicePin;
    private String devicePicture;

}
