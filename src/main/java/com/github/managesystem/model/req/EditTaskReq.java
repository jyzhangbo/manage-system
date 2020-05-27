package com.github.managesystem.model.req;


import lombok.Data;

import java.util.List;

@Data
public class EditTaskReq {

    private String taskNum;

    private List<String> devices;

    /**
     * 状态 1:开启  2:关闭
     */
    private Integer state;

}
