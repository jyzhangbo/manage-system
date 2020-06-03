package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 17:29
 */
@Data
public class ListTaskReq {

    private String taskNum;
    private String taskName;
    private Integer taskState;

    public Integer pageNum;

    public Integer pageSize;

}
