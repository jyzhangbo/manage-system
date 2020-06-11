package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/6/1 19:22
 */
@Data
public class QueryDataHistoryApp {

    private String time;

    private List<QueryDataHistoryValue> attributes = new ArrayList<>();

}
