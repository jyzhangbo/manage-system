package com.github.managesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alarm_log")
public class AlarmLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 告警对象
     */
    private String alarmRuleObject;

    /**
     * 告警条件
     */
    private String alarmRuleCondition;

    /**
     * 公司
     */
    private String companyName;

    /**
     * 设备编号
     */
    private String deviceNum;

    /**
     * 采集点
     */
    private String collectionPoint;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 最后修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime modifyTime;

    /**
     * 是否删除(0.未删除 1.已删除)
     */
    private Integer isDel;

    /**
     * 备注
     */
    private String remark;


    public static final String ID = "id";

    public static final String ALARM_RULE_OBJECT = "alarm_rule_object";

    public static final String ALARM_RULE_CONDITION = "alarm_rule_condition";

    public static final String COMPANY_NAME = "company_name";

    public static final String DEVICE_NUM = "device_num";

    public static final String COLLECTION_POINT = "collection_point";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_TIME = "modify_time";

    public static final String IS_DEL = "is_del";

    public static final String REMARK = "remark";

}
