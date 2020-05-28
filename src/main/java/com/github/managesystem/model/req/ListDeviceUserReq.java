package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 14:33
 */
@Data
public class ListDeviceUserReq {

    private String deviceNum;
    private String deviceName;

    public Integer pageNum;

    public Integer pageSize;

}
