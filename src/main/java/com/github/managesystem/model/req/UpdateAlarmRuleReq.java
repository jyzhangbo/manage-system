package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @author kai
 * @date 2020/5/29
 */
@Data
public class UpdateAlarmRuleReq {
    private Long id;
    private Integer judgeValue;
}
