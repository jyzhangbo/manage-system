package com.github.managesystem.model.req;

import lombok.Data;

@Data
public class AddUserReq {

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 公司名称
     */
    private String companyName;

    private boolean admin;
}
