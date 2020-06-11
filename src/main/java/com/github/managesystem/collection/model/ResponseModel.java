package com.github.managesystem.collection.model;

import com.github.managesystem.entity.DeviceControlRecord;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/6 18:22
 */
@Data
public class ResponseModel {

    private String devNum;
    private String command;

    private List<DeviceControlRecord> records = new ArrayList<>();

}
