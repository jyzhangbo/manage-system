package com.github.managesystem.collection.handle.encoder;

import com.github.managesystem.collection.model.ResponseModel;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.TransformUtils;

import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/6/8 12:42
 */
public class AckEncoder {

    public static StringBuilder encoder(ResponseModel msg){
        StringBuilder content = new StringBuilder();
        String now = TimeUtils.formatTime(LocalDateTime.now());
        String year = TransformUtils.integerStrToHexString(now.substring(0,4));
        String mounth = TransformUtils.integerStrToHexString(now.substring(5,7));
        String day = TransformUtils.integerStrToHexString(now.substring(8,10));

        String hour = TransformUtils.integerStrToHexString(now.substring(11,13));
        String minute = TransformUtils.integerStrToHexString(now.substring(14,16));
        String second = TransformUtils.integerStrToHexString(now.substring(17));
        content.append("029A")
                .append(msg.getOldCommand())
                .append("81")
                .append(year)
                .append(mounth)
                .append(day)
                .append(hour).append(minute).append(second);
        return content;
    }

}
