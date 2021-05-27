package com.github.managesystem.model.req;

import lombok.Data;

import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/30 14:40
 */
@Data
public class ChangeDataReq {

    private String taskNum;
    private String deviceNum;
    private String time;
    private Map<String,String> values;

}
