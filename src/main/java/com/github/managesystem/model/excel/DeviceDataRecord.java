package com.github.managesystem.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @Author:zhangbo
 * @Date:2020/6/12 17:02
 */
@Data
public class DeviceDataRecord {

    private String time;

    /**
     * 温度
     */
    private String attributeT1;

    /**
     * 温度
     */
    private String attributeT2;

    /**
     * 温度
     */
    private String attributeT3;

    /**
     * 温度
     */
    private String attributeT4;

    /**
     * 温度
     */
    private String attributeT5;

    /**
     * 温度
     */
    private String attributeT6;

    /**
     * 温度
     */
    private String attributeT7;

    /**
     * 温度
     */
    private String attributeT8;
}
