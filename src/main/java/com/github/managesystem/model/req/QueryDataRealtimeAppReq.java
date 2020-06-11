package com.github.managesystem.model.req;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Author:zhangbo
 * @Date:2020/6/2 9:55
 */
@Data
public class QueryDataRealtimeAppReq {

    private String taskNum;
    private String deviceNum;

}
