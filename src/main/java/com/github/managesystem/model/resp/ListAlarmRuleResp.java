package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/19 15:30
 */
@Data
@Builder
public class ListAlarmRuleResp {

    private List<ListAlarmRuleInfo> infos;

    private Integer total;

}
