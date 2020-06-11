package com.github.managesystem.model.resp;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/2 9:55
 */
@Data
public class QueryDataRealtimeAppResp {

    private String time;
    private List<QueryDataHistoryValue> attributes = new ArrayList<>();

}
