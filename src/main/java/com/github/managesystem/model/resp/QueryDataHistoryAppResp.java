package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/1 19:22
 */
@Data
@Builder
public class QueryDataHistoryAppResp {

    private Long pageTotal;
    private List<QueryDataHistoryApp> historyData;

}
