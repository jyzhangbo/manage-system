package com.github.managesystem.collection.handle.encoder;

import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.util.TransformUtils;

/**
 * @Author:zhangbo
 * @Date:2020/6/8 12:40
 */
public class ControlEncoder {

    public static StringBuilder encoder(ResponseModel msg){
        StringBuilder content = new StringBuilder();
        String length = TransformUtils.integerStrToHexString(String.valueOf(msg.getRecords().size()));
        content.append(length);
        for(DeviceControlRecord record : msg.getRecords()){
            content.append(record.getControlType());
            content.append(record.getControlData());
        }
        return content;
    }

}
