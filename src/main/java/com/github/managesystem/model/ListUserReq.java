package com.github.managesystem.model;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/18 17:40
 */
@Data
public class ListUserReq {

    private String companyName;
    private String phone;
    private String loginName;

}
