package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/18 18:11
 */
@Data
@Builder
public class ListUserInfo {

    private String companyName;
    private String phone;
    private String loginName;
    private String password;

}
