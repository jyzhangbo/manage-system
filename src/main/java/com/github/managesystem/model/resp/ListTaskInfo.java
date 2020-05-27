package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 17:40
 */
@Data
@Builder
public class ListTaskInfo {

    private String taskName;
    private String taskNum;
    private Integer taskStatus;
    private String startTime;
    private String endTime;
    private List<DeviceInfo> devices;

}
