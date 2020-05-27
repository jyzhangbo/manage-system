package com.github.managesystem.model.constant;

import lombok.Getter;

/**
 * 消息类型.
 *
 * @Author:zhangbo
 * @Date:2018/5/18 17:42
 */
@Getter
public enum RoleEnum {

    ADMIN("admin"),
    USERADMIN("userAdmin"),
    USER("user");

    public String value;

    private RoleEnum(String value) {
        this.value = value;
    }
}
