package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/29 10:44
 */
@Data
public class QueryDataTableReq {

    private String taskNum;
    private String deviceNum;
    private Integer pageNum;
    private Integer pageSize;

}
