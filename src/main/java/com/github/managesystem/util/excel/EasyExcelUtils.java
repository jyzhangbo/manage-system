package com.github.managesystem.util.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.WriteHandler;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.google.common.collect.Lists;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author kai
 */
@Slf4j
public class EasyExcelUtils {
    /**
     * 读取 Excel(多个 sheet)
     *
     * @param inputStream
     *            文件
     * @param clazz
     *            实体类映射，继承 BaseRowModel 类
     * @return Excel 数据 list
     */
    public static <E extends BaseRowModel> List<E> readExcel(InputStream inputStream, ExcelListener<E> listener,
            Class<? extends BaseRowModel> clazz) {
        listener.setDatas(Lists.newArrayList());
        ExcelReader reader = getReader(inputStream, listener);
        if (reader == null) {
            return null;
        }
        for (Sheet sheet : reader.getSheets()) {
            if (clazz != null) {
                sheet.setClazz(clazz);
            }
            reader.read(sheet);
        }
        return listener.getDatas();
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param inputStream
     *            文件
     * @param clazz
     *            实体类映射，继承 BaseRowModel 类
     * @param sheetNo
     *            sheet 的序号 从1开始
     * @return Excel 数据 list
     */
    public static <E extends BaseRowModel> List<E> readExcel(InputStream inputStream,
            Class<? extends BaseRowModel> clazz, ExcelListener<E> listener, int sheetNo) throws CodeException {
        return readExcel(inputStream, clazz, listener, sheetNo, 1);
    }

    /**
     * 读取某个 sheet 的 Excel
     *
     * @param inputStream
     *            文件
     * @param clazz
     *            实体类映射，继承 BaseRowModel 类
     * @param sheetNo
     *            sheet 的序号 从1开始
     * @param headLineNum
     *            表头行数，默认为1
     * @return Excel 数据 list
     */
    public static <E extends BaseRowModel> List<E> readExcel(InputStream inputStream,
            Class<? extends BaseRowModel> clazz, ExcelListener<E> listener, int sheetNo,
            int headLineNum) throws CodeException {
        listener.setDatas(Lists.newArrayList());
        ExcelReader reader = getReader(inputStream, listener);
        if (reader == null) {
            return null;
        }
        try {
            reader.read(new Sheet(sheetNo, headLineNum, clazz));
        } catch (NumberFormatException e) {
            throw new CodeException(ResultCode.ERROR_METHOD_REQUEST);
        }

        return listener.getDatas();
    }

    /**
     * 导出 Excel ：一个 sheet，带表头
     *
     * @param list
     *            数据 list，每个元素为一个 BaseRowModel
     * @param outputStream
     *            输入流
     */
    public static void writeExcel(List<? extends BaseRowModel> list, Class<? extends BaseRowModel> clazz,
            WriteHandler handler, ServletOutputStream outputStream) {
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, outputStream, ExcelTypeEnum.XLSX, true,
                handler);
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setAutoWidth(Boolean.TRUE);
        writer.write(list, sheet);

        writer.finish();
    }

    public static void downloadExcelFile(String title, List<? extends BaseRowModel> list,
            Class<? extends BaseRowModel> clazz, HttpServletResponse response, WriteHandler handler) {
        try {
            @Cleanup
            ServletOutputStream out = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String fileName = new String(title.getBytes(), "iso-8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            writeExcel(list, clazz, handler, out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("下载文件错误", e);
        }
    }

    public static void downloadExcelFileDynamicHead(String title, List<? extends BaseRowModel> list,
                                                    Class<? extends BaseRowModel> clazz, HttpServletResponse response,
                                                    WriteHandler handler, List<List<String>> heads) {
        try {
            @Cleanup
            ServletOutputStream out = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String fileName = new String(title.getBytes(), "iso-8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            writeExcelDynamicHead(list,clazz,handler, out,heads);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("下载文件错误", e);
        }
    }

    public static void writeExcelDynamicHead(List<? extends BaseRowModel> list, Class<? extends BaseRowModel> clazz,
                                             WriteHandler handler, ServletOutputStream outputStream, List<List<String>> heads) {
        ExcelWriter writer = EasyExcelFactory.getWriterWithTempAndHandler(null, outputStream, ExcelTypeEnum.XLSX, true,
                handler);
        Sheet sheet = new Sheet(1, 0, clazz);
        sheet.setAutoWidth(Boolean.TRUE);
        sheet.setHead(heads);
        writer.write(list, sheet);

        writer.finish();
    }


    protected static void merge(ExcelWriter writer, Sheet sheet, List<? extends BaseRowModel> datas, int firstRow,
            int lastRow,
            int mergeColIndex) {
        writer.write(datas, sheet);
        writer.merge(firstRow, lastRow, mergeColIndex, mergeColIndex);
    }

    /**
     * 返回 ExcelReader
     *
     * @param inputStream
     *            需要解析的 Excel 文件
     * @param excelListener
     *            new ExcelListener()
     */
    private static ExcelReader getReader(InputStream inputStream, ExcelListener excelListener) {
        return new ExcelReader(inputStream, null, excelListener, true);
    }

    public static void writeSimpleBySheet(List<List<Object>> data, List<String> head, String sheetName, OutputStream outputStream) {
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName(sheetName);
        sheet.setAutoWidth(Boolean.TRUE);
        if (head != null) {
            List<List<String>> list = new ArrayList<>();
            head.forEach(h -> list.add(Collections.singletonList(h)));
            sheet.setHead(list);
        }


        ExcelWriter writer = null;
        try {
            writer = EasyExcelFactory.getWriter(outputStream);
            writer.write1(data, sheet);
        } finally {
            if (writer != null) {
                writer.finish();
            }
        }

    }
}
