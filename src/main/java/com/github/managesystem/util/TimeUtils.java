package com.github.managesystem.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 时间辅助类.
 *
 * @author zhangjianshe
 */
public class TimeUtils {


    /**
     * 解析时间数据.
     *
     * @param data   the data
     * @param format the format
     * @return the timestamp
     */
    public static final Timestamp parseTime(String data, String format) {
        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return new Timestamp(df.parse(data).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return now();
        }
    }

    /**
     * Parses the time.
     *
     * @param data the data
     * @return the timestamp
     */
    public static final Timestamp parseTime(String data) {
        return parseTime(data, null);
    }

    /**
     * Parses the time.
     *
     * @param appStartTime the app start time
     * @return the timestamp
     */
    public static Timestamp parseTime(Long appStartTime) {
        return new Timestamp(appStartTime);
    }

    /**
     * Format time.
     *
     * @param time the time
     * @return the string
     */
    public static final String formatTime(Timestamp time) {
        if (time == null) {
            return "";
        }
        return formatTime(time.getTime(), "");
    }

    /**
     * Format time.
     *
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(Timestamp time, String format) {
        if (time == null) {
            return "";
        }
        return formatTime(time.getTime(), format);
    }


    /**
     * @param time   the time
     * @param format the format
     * @return the string
     */
    public static final String formatTime(long time, String format) {

        if (format == null || format.length() == 0) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }

    public static final String formatTime(LocalDateTime time){
       return time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }


    /**
     * Format time.
     *
     * @param time the time
     * @return the string
     */
    public static final String formatTime(long time) {
        return formatTime(time, "");
    }


    /**
     * 当前的时间.
     *
     * @return the timestamp
     */
    public static Timestamp now() {

        return new Timestamp(System.currentTimeMillis());
    }

}
