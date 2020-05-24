package com.github.managesystem.model.resp;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QueryDataTableResp {

    private List<QueryDataTable> datas;

}
