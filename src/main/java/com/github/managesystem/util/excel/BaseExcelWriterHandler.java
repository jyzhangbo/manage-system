package com.github.managesystem.util.excel;

import com.alibaba.excel.event.WriteHandler;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;

@Component
public class BaseExcelWriterHandler implements WriteHandler {

    @Override
    public void sheet(int i, Sheet sheet) {
    }

    @Override
    public void row(int i, Row row) {

    }

    @Override
    public void cell(int i, Cell cell) {
        Workbook workbook = cell.getSheet().getWorkbook();
        if (cell.getRowIndex() == 0) {
            CellStyle cellStyle = workbook.createCellStyle();
            // 设置样式
            // 注意：样式最好采用公用样式，样式在创建sheet后创建，如果有多个样式也需要在创建sheet时候创建后面直接使用，不要每个Cell Create 一个样式，不然会导致报错 The maximum number
            Font font = workbook.createFont();
            font.setBold(true);// 粗体显示
            cellStyle.setFont(font);
            cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION); // 水平居中
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中
            cell.setCellStyle(cellStyle);
        }
    }
}
