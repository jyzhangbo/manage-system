package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 9:47
 */
@Data
public class ListDeviceAdminReq {

    private String deviceNum;
    private String companyName;

    public Integer pageNum;

    public Integer pageSize;

}
