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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DeviceDataRecord extends BaseRowModel {

    @ExcelProperty(index = 1)
    private String time;

    /**
     * 温度
     */
    @ExcelProperty(index = 2)
    private Double attributeT1;

    /**
     * 温度
     */
    @ExcelProperty(index = 3)
    private Double attributeT2;

    /**
     * 温度
     */
    @ExcelProperty(index = 4)
    private Double attributeT3;

    /**
     * 温度
     */
    @ExcelProperty(index = 5)
    private Double attributeT4;

    /**
     * 温度
     */
    @ExcelProperty(index = 6)
    private Double attributeT5;

    /**
     * 温度
     */
    @ExcelProperty(index = 7)
    private Double attributeT6;

    /**
     * 温度
     */
    @ExcelProperty(index = 8)
    private Double attributeT7;

    /**
     * 温度
     */
    @ExcelProperty(index = 9)
    private Double attributeT8;
}
