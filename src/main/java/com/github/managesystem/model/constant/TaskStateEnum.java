package com.github.managesystem.model.constant;

import lombok.Getter;

/**
 * 消息类型.
 *
 * @Author:zhangbo
 * @Date:2018/5/18 17:42
 */
@Getter
public enum TaskStateEnum {

    CREATE(0),
    START(1),
    END(2);

    public Integer value;

    private TaskStateEnum(Integer value) {
        this.value = value;
    }
}
