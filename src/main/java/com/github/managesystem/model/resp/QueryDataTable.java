package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryDataTable {

    private String time;

    private List<Double> values;

}
