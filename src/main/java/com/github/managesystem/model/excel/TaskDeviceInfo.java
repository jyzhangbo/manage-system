package com.github.managesystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/6/12 17:02
 */
@Data
@Builder
public class TaskDeviceInfo {

    /**
     * 任务编号
     */
    private String taskNum;

    /**
     * 任务名称
     */
    private String taskName;

    private String deviceName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
