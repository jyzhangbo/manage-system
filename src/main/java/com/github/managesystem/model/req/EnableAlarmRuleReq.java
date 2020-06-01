package com.github.managesystem.model.req;

import lombok.Data;

/**
 * @author kai
 * @date 2020/5/29
 */
@Data
public class EnableAlarmRuleReq {
    private Long id;
    private Integer isEnable;
    private String modifer;
}
