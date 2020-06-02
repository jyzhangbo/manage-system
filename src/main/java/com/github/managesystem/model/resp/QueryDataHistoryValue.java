package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

/**
 * @Author:zhangbo
 * @Date:2020/6/1 19:24
 */
@Data
@Builder
public class QueryDataHistoryValue {

    private String attributeName;
    private Double attributeValue;
}
