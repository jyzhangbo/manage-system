package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class QueryDataTableResp {

    private List<QueryDataTable> datas;


    private Map<String,String> tableHeader;

    private String deviceImg;

    private Long total;

}
