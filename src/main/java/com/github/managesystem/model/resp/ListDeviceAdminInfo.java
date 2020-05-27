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
public class ListDeviceAdminInfo {

    private String companyName;
    private String deviceNum;
    private String deviceName;

}
