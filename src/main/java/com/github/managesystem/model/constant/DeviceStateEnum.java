package com.github.managesystem.model.constant;

import lombok.Getter;

/**
 * 消息类型.
 *
 * @Author:zhangbo
 * @Date:2018/5/18 17:42
 */
@Getter
public enum DeviceStateEnum {

    UNUSE(0),
    USING(1);

    public Integer value;

    private DeviceStateEnum(Integer value) {
        this.value = value;
    }
}
