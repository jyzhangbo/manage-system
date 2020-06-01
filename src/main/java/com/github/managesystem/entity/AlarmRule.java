package com.github.managesystem.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.managesystem.model.constant.JudgeTypeEnum;
import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_alarm_rule")
public class AlarmRule implements Serializable {

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
     * 告警规则名称
     */
    private String alarmRuleName;

    /**
     * 判断类型 1.= 2.> 3.< 4.≥ 5.≤
     */
    private String judgeType;

    /**
     * 判断变化
     */
    private Integer judgeValue;

    /**
     * 数值单位
     */
    private String judgeUnit;

    /**
     * 公司
     */
    private String companyName;

    /**
     * 告警规则状态 1启用 0禁用
     */
    private Integer isEnable;

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
     * 创建人
     */
    private String creater;

    /**
     * 最后修改人
     */
    private String modifer;

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

    public static final String ALARM_RULE_NAME = "alarm_rule_name";

    public static final String JUDGE_TYPE = "judge_type";

    public static final String JUDGE_VALUE = "judge_value";

    public static final String JUDGE_UNIT = "judge_unit";

    public static final String COMPANY_NAME = "company_name";

    public static final String IS_ENABLE = "is_enable";

    public static final String CREATE_TIME = "create_time";

    public static final String MODIFY_TIME = "modify_time";

    public static final String CREATER = "creater";

    public static final String MODIFER = "modifer";

    public static final String IS_DEL = "is_del";

    public static final String REMARK = "remark";

}
