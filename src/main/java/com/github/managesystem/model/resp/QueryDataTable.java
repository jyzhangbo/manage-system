package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class QueryDataTable {

    private String time;

    private Map<String,String> values;

    private boolean show = false;

}
