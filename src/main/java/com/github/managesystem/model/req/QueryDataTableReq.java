package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/29 10:44
 */
@Data
public class QueryDataTableReq {

    private String startTime;
    private String endTime;
    private String taskNum;
    private String deviceNum;

}
