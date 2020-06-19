package com.github.managesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.github.managesystem.model.constant.AttributeEnum;
import com.github.managesystem.model.resp.QueryDataHistoryValue;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_device_data")
@Builder
public class DeviceData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 任务编号
     */
    private String taskNum;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 数据时间
     */
    private LocalDateTime dataTime;

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


    public static final String ID = "id";

    public static final String TASK_NUM = "task_num";

    public static final String DEVICE_NUM = "device_num";

    public static final String DATA_TIME = "data_time";

    public static final String ATTRIBUTE_T1 = "attribute_t1";

    public static final String ATTRIBUTE_T2 = "attribute_t2";

    public static final String ATTRIBUTE_T3 = "attribute_t3";

    public static final String ATTRIBUTE_T4 = "attribute_t4";

    public static final String ATTRIBUTE_T5 = "attribute_t5";

    public static final String ATTRIBUTE_T6 = "attribute_t6";

    public static final String ATTRIBUTE_T7 = "attribute_t7";

    public static final String ATTRIBUTE_T8 = "attribute_t8";

    public String getValueByAttributeCode(String attributeCode) {
        switch (attributeCode) {
            case "T1" :
                return this.getAttributeT1();
            case "T2" :
                return this.getAttributeT2();
            case "T3" :
                return this.getAttributeT3();
            case "T4" :
                return this.getAttributeT4();
            case "T5" :
                return this.getAttributeT5();
            case "T6" :
                return this.getAttributeT6();
            case "T7" :
                return this.getAttributeT7();
            case "T8" :
                return this.getAttributeT8();
            default :
                break;
        }

        return "0.0";
    }

    public DeviceData copyValueToAttribute(String attributeCode, String value) {
        switch (attributeCode) {
            case "T1" :
                this.setAttributeT1(value);
                break;
            case "T2" :
                this.setAttributeT2(value);
                break;
            case "T3" :
                this.setAttributeT3(value);
                break;
            case "T4" :
                this.setAttributeT4(value);
                break;
            case "T5" :
                this.setAttributeT5(value);
                break;
            case "T6" :
                this.setAttributeT6(value);
                break;
            case "T7" :
                this.setAttributeT7(value);
                break;
            case "T8" :
                this.setAttributeT8(value);
                break;
            default :
                break;
        }

        return this;
    }

}
