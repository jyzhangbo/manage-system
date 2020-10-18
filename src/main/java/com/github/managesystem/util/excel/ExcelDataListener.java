package com.github.managesystem.util.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.model.excel.DeviceDataRecord;
import com.github.managesystem.service.IDeviceDataService;
import com.github.managesystem.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelDataListener extends AnalysisEventListener<DeviceDataRecord> {

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 200;
    List<DeviceData> list = new ArrayList<DeviceData>();

    private IDeviceDataService deviceDataService;
    private String taskNum;
    private String deviceNum;

    public ExcelDataListener(IDeviceDataService deviceDataService,String taskNum,String deviceNum){
        this.deviceDataService = deviceDataService;
        this.taskNum = taskNum;
        this.deviceNum = deviceNum;
    }

    @Override
    public void invoke(DeviceDataRecord data, AnalysisContext context) {
        list.add(DeviceData.builder().taskNum(this.taskNum)
                .deviceNum(this.deviceNum)
                .dataTime(TimeUtils.parseTime(data.getTime()))
                .attributeT1(data.getAttributeT1())
                .attributeT2(data.getAttributeT2())
                .attributeT3(data.getAttributeT3())
                .attributeT4(data.getAttributeT4())
                .attributeT5(data.getAttributeT5())
                .attributeT6(data.getAttributeT6())
                .attributeT7(data.getAttributeT7())
                .attributeT8(data.getAttributeT8()).build());
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        deviceDataService.saveBatch(list);
        log.info("存储数据库成功！");
    }

}
