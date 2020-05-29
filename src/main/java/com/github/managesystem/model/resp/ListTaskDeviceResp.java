package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/29 9:49
 */
@Data
@Builder
public class ListTaskDeviceResp {

    private String value;
    private String label;
    private List<Map<String,String>> children;

}
