package com.github.managesystem.util.excel;

import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kai94
 */
public abstract class ExcelListener<E> extends AnalysisEventListener<E> {
    private List<E> datas = new ArrayList<>();

    public List<E> getDatas() {
        return datas;
    }

    public void setDatas(List<E> datas) {
        this.datas = datas;
    }
}
