package com.github.managesystem.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.excel.DeviceDataRecord;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.DataService;
import com.github.managesystem.service.ITaskDeviceService;
import com.github.managesystem.util.AsertUtils;
import com.github.managesystem.util.TimeUtils;
import com.github.managesystem.util.excel.BaseExcelWriterHandler;
import com.github.managesystem.util.excel.EasyExcelUtils;
import org.nutz.json.Json;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 14:52
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private BaseExcelWriterHandler baseExcelWriterHandler;

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @PostMapping(value = "/simulation")
    public Result simulationData(@RequestBody SimulationDataReq req) throws CodeException{
        dataService.simulationData(req);
        return Result.ok();
    }

    @PostMapping(value = "/query/char")
    public Result queryDataChar(@RequestBody QueryDataTableReq req) throws CodeException {
        return Result.ok(dataService.queryDataChar(req));
    }

    @PostMapping(value = "/query/table")
    public Result queryDataTable(@RequestBody QueryDataTableReq req) throws CodeException {
        return Result.ok(dataService.queryDataTable(req));
    }

    @PostMapping(value = "/change")
    public Result changeData(@RequestBody ChangeDataReq req) throws CodeException {
        dataService.changeData(req);
        return Result.ok();
    }

    @PostMapping(value = "/copy")
    public Result copyData(@RequestBody CopyDataReq req,HttpServletRequest request) throws CodeException {
        dataService.copyData(req,request);
        return Result.ok();
    }

    @PostMapping(value = "/query/history/app")
    public Result queryDataHistoryApp(@RequestBody QueryDataHistoryAppReq req,HttpServletRequest request) throws CodeException {
        return Result.ok(dataService.queryDataHistoryApp(req,request));
    }

    @PostMapping(value = "/query/realtime/app")
    public Result queryDataRealtimeApp(@RequestBody QueryDataRealtimeAppReq req,HttpServletRequest request) throws CodeException {
        return Result.ok(dataService.queryDataRealtimeApp(req,request));
    }

    @GetMapping(value = "/export/excel")
    public void failureRows(@RequestParam("param") String param, HttpServletResponse response) {

        QueryDataTableReq paramReq = Json.fromJson(QueryDataTableReq.class,param);
        TaskDevice taskDevice = taskDeviceService.getOne(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, paramReq.getTaskNum())
                .eq(TaskDevice.DEVICE_NUM, paramReq.getDeviceNum()), false);
        Map<String,String> attributeInfo = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());
        List<List<String>> heads = new ArrayList<List<String>>();
        heads.add(Arrays.asList("时间"));
        for(String value : attributeInfo.values()){
            List<String> head = new ArrayList<String>();
            head.add(value);
            heads.add(head);
        }


        paramReq.setPageNum(1);
        paramReq.setPageSize(10000);

        paramReq.setEndTime(AsertUtils.asertToNow(paramReq.getEndTime()));
        IPage<DeviceData> record =dataService.queryDeviceDataPage(paramReq);
        List<DeviceDataRecord> datas = new ArrayList<>();
        for(DeviceData deviceData : record.getRecords()){
            DeviceDataRecord deviceDataRecord = new DeviceDataRecord();
            BeanUtils.copyProperties(deviceData,deviceDataRecord);
            deviceDataRecord.setTime(TimeUtils.formatTime(deviceData.getDataTime()));
            datas.add(deviceDataRecord);
        }


        EasyExcelUtils.downloadExcelFileDynamicHead(
                String.format("%s-%s",taskDevice.getTaskName(),taskDevice.getDeviceName()),
                datas,
                DeviceDataRecord.class,
                response,
                baseExcelWriterHandler,
                heads);
    }

}
