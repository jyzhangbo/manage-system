package com.github.managesystem.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelDataListener extends AnalysisEventListener<Map<String,String>> {




    @Override
    public void invoke(Map<String, String> data, AnalysisContext context) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
