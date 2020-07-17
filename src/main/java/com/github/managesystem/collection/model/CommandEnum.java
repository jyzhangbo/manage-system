package com.github.managesystem.collection.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author:zhangbo
 * @Date:2020/6/8 15:10
 */
@AllArgsConstructor
@Getter
public enum CommandEnum {

    COMMAND_86("86"),
    COMMAND_81("81"),
    COMMAND_8A("8A"),
    COMMAND_8B("8B"),
    COMMAND_8C("8C"),
    COMMAND_8D("8D");

    private String value;

}
