package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/6/1 19:20
 */
@Data
public class QueryDataHistoryAppReq {

    private String taskNum;
    private String deviceNum;
    private Long startTime;
    private Long endTime;
    private Integer pageSize;
    private Integer pageNum;

}
