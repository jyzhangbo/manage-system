package com.github.managesystem.model.req;

import lombok.Data;

import java.util.List;

@Data
public class AddTaskReq {

    private String taskNum;

    private List<String> devices;

    private String taskName;
}
