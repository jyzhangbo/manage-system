package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/14 17:14
 */
@Data
@Builder
public class UserInfoResp {

    private List<String> roles;

    private String introduction;

    private String avatar;

    private String name;

}
