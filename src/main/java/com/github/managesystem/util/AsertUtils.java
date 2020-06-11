package com.github.managesystem.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.Task;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import org.nutz.lang.Strings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * @Author:zhangbo
 * @Date:2020/5/30 15:37
 */
public class AsertUtils {

    public static Long asertToZeroHour(Long time){
        if(Objects.isNull(time)){
            return LocalDateTime.of(LocalDate.now(),LocalTime.MIN).toEpochSecond(ZoneOffset.ofHours(8)) * 1000;
        }else {
            return time;
        }
    }

    public static String asertToNow(String time){
        if(Strings.isBlank(time)){
            return TimeUtils.formatTime(LocalDateTime.now());
        }else {
            return time;
        }
    }

    public static Long asertToNow(Long time){
        if(Objects.isNull(time)){
            return LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8)) * 1000;
        }else {
            return time;
        }
    }

}
