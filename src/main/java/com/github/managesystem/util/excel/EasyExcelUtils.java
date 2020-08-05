package com.github.managesystem.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.github.managesystem.model.excel.DeviceDataRecord;
import com.github.managesystem.model.excel.TaskDeviceInfo;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author kai
 */
@Slf4j
public class EasyExcelUtils {


    public static void downloadExcelModel(HttpServletResponse response,
                                          String title,List<DeviceDataRecord> datas,
                                          TaskDeviceInfo taskDeviceInfo,
                                          Map<String,String> attributeInfo){
        try {
            ServletOutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = new String(title.getBytes(), "iso-8859-1");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            writeExcelModel(out,datas,taskDeviceInfo,attributeInfo);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("下载文件错误", e);
        }
    }

    public static void writeExcelModel(ServletOutputStream out,List<DeviceDataRecord> datas,TaskDeviceInfo taskDeviceInfo,Map<String,String> attributeInfo) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // {} 代表普通变量 {.} 代表是list的变量
        String templateFileName = "/home/soft/demo.xlsx";

        ExcelWriter excelWriter = EasyExcel.write(out).withTemplate(templateFileName).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        // 这里注意 入参用了forceNewRow 代表在写入list的时候不管list下面有没有空行 都会创建一行，然后下面的数据往后移动。默认 是false，会直接使用下一行，如果没有则创建。
        // forceNewRow 如果设置了true,有个缺点 就是他会把所有的数据都放到内存了，所以慎用
        // 简单的说 如果你的模板有list,且list不是最后一行，下面还有数据需要填充 就必须设置 forceNewRow=true 但是这个就会把所有数据放到内存 会很耗内存
        // 如果数据量大 list不是最后一行 参照下一个
        excelWriter.fill(datas, writeSheet);
        excelWriter.fill(attributeInfo, writeSheet);
        excelWriter.fill(taskDeviceInfo, writeSheet);
        excelWriter.finish();
    }

}
