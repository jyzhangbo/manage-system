package com.github.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.constant.AttributeEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.util.AsertUtils;
import com.github.managesystem.util.TimeUtils;
import org.nutz.json.Json;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @Author:zhangbo
 * @Date:2020/5/29 14:58
 */
@Service
public class DataService {

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private IDeviceDataService deviceDataService;


    public QueryDataCharResp queryDataChar(QueryDataTableReq req) throws CodeException{

        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());
        req.setDeviceNum(taskDevice.getDeviceNum());

        List<DeviceData> datas = deviceDataService.list(new QueryWrapper<DeviceData>()
                .eq(DeviceData.TASK_NUM, req.getTaskNum())
                .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                .orderByAsc(DeviceData.DATA_TIME));

        QueryDataCharResp queryDataCharResp = AttributeEnum.deviceDataToChart(datas, tableHeader);
        queryDataCharResp.setDeviceImg(taskDevice.getDeviceImg());
        queryDataCharResp.setDeviceName(taskDevice.getDeviceName());
        queryDataCharResp.setCollectSpace(taskDevice.getCollectSpace());
        Map<String, String> attribute = Json.fromJsonAsMap(String.class, taskDevice.getAttributeInfo());
        List<AttributeInfo> infos = new ArrayList<>();
        for(Map.Entry<String,String> entry : attribute.entrySet()){
            infos.add(AttributeInfo.builder().code(entry.getKey()).name(entry.getValue()).build());
        }
        queryDataCharResp.setAttributeInfo(infos);
        return queryDataCharResp;
    }

    public QueryDataTableResp queryDataTable(QueryDataTableReq req) throws CodeException{

        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());
        req.setDeviceNum(taskDevice.getDeviceNum());

        IPage<DeviceData> record = queryDeviceDataPage(req);

        List<QueryDataTable> tableDatas = new ArrayList<>();
        for(DeviceData data : record.getRecords()){
            tableDatas.add(QueryDataTable.builder()
                    .time(TimeUtils.formatTime(data.getDataTime()))
                    .values(AttributeEnum.deviceDataToMap(data)).build());
        }

        return QueryDataTableResp.builder()
                .total(record.getTotal())
                .deviceImg(taskDevice.getDeviceImg())
                .tableHeader(tableHeader)
                .datas(tableDatas)
                .build();
    }


    public IPage<DeviceData> queryDeviceDataPage(QueryDataTableReq req){
        return deviceDataService.page(
                new Page<>(req.getPageNum(),req.getPageSize()),
                new QueryWrapper<DeviceData>()
                        .eq(DeviceData.TASK_NUM, req.getTaskNum())
                        .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                        .orderByDesc(DeviceData.DATA_TIME));
    }

    public IPage<DeviceData> queryDeviceDataPageAsc(QueryDataTableReq req){
        return deviceDataService.page(
                new Page<>(req.getPageNum(),req.getPageSize()),
                new QueryWrapper<DeviceData>()
                        .eq(DeviceData.TASK_NUM, req.getTaskNum())
                        .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                        .orderByAsc(DeviceData.DATA_TIME));
    }


    public void changeData(ChangeDataReq req) {

        UpdateWrapper<DeviceData> wrapper = new UpdateWrapper<>();
        wrapper.eq(DeviceData.TASK_NUM, req.getTaskNum())
                .eq(DeviceData.DEVICE_NUM, req.getDeviceNum()).eq(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getTime()));
        for(Map.Entry<String,String> entry : req.getValues().entrySet()){
            wrapper.set(AttributeEnum.getAttributeName(entry.getKey()),entry.getValue());
        }
        deviceDataService.update(wrapper);

    }

    /**
     * 模拟数据
     * @param req
     */
    public void simulationData(SimulationDataReq req) {
        Long startTime = TimeUtils.parseTime(req.getStartTime()).toEpochSecond(ZoneOffset.ofHours(8));
        Long stableTime = TimeUtils.parseTime(req.getStableTime()).toEpochSecond(ZoneOffset.ofHours(8));
        Long downTime = TimeUtils.parseTime(req.getDownTime()).toEpochSecond(ZoneOffset.ofHours(8));
        Long endTime = TimeUtils.parseTime(req.getEndTime()).toEpochSecond(ZoneOffset.ofHours(8));
        Random r = new Random();

        List<DeviceData> deviceDatasOld = deviceDataService.list(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM, req.getTaskNum())
                .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                .between(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getStartTime()), TimeUtils.parseTime(req.getEndTime()))
                .orderByAsc(DeviceData.DATA_TIME));

        if(req.getListTemp().size() == 8 || deviceDatasOld.size() == 0){
            int timeSpace = req.getTimeSpace() * 60;
            //全部模拟
            int second = new Double(req.getRandomTime() * 60).intValue();
            List<DeviceData> deviceDatas = new LinkedList<>();
            long dataTime = startTime;
            while (dataTime< endTime){
                DeviceData deviceData = DeviceData.builder().taskNum(req.getTaskNum())
                        .deviceNum(req.getDeviceNum())
                        .dataTime(TimeUtils.parseTime(dataTime)).build();
                deviceDatas.add(createRandomData(r, req, startTime, stableTime, downTime, endTime, dataTime, deviceData));
                dataTime += timeSpace+ R.random(-second, second);

            }

            deviceDataService.remove(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM,req.getTaskNum())
                         .eq(DeviceData.DEVICE_NUM,req.getDeviceNum())
                         .between(DeviceData.DATA_TIME,TimeUtils.parseTime(req.getStartTime()),TimeUtils.parseTime(req.getEndTime())));

            deviceDataService.saveBatch(deviceDatas);

        }else {
            //部分模拟(时间不变)
            List<DeviceData> newDeviceDatas = new ArrayList<>();
            for(DeviceData deviceData : deviceDatasOld){
                long dataTime = deviceData.getDataTime().toEpochSecond(ZoneOffset.ofHours(8));
                newDeviceDatas.add(createRandomData(r,req, startTime, stableTime, downTime, endTime, dataTime, deviceData));
            }

            deviceDataService.updateBatchById(newDeviceDatas);
        }

    }

    private DeviceData createRandomData(Random r, SimulationDataReq req, Long startTime, Long stableTime, Long downTime, Long endTime, long dataTime, DeviceData deviceData) {
        for(SimulationData data : req.getListTemp()) {
            //计算温度值
            double temp = 0;
            double startTemp = data.getStartTemp();
            double stableTemp = data.getStableTemp();
            double downTemp = data.getDownTemp();
            double endTemp = data.getEndTemp();
            if (dataTime < stableTime) {
                temp = startTemp + (stableTemp - startTemp) / (stableTime - startTime) * (dataTime - startTime);
            } else if (dataTime > stableTime && dataTime < downTime) {
                temp = stableTemp + (downTemp - stableTemp) / (downTime - stableTime) * (dataTime - stableTime);
            } else {
                temp = downTemp + (endTemp - downTemp) / (endTime - downTime) * (dataTime - downTime);
            }

            double randomTemp = 0;
            if (r.nextBoolean()) {
                randomTemp = temp + r.nextDouble() * data.getRandomData();
            } else {
                randomTemp = temp - r.nextDouble() * data.getRandomData();
            }
            deviceData.copyValueToAttribute(data.getCode(), new BigDecimal(randomTemp).setScale(1,BigDecimal.ROUND_HALF_UP).toString());
        }
        return deviceData;
    }

    public void copyData(CopyDataReq req,HttpServletRequest request) throws CodeException{

        //复制数据
        List<DeviceData> deviceDatas = deviceDataService.list(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM, req.getTaskNum())
                .eq(DeviceData.DEVICE_NUM, req.getDeviceNum()));
        List<DeviceData> newDeviceDatas = new ArrayList<>();
        Random r= new Random();
        for(DeviceData deviceData : deviceDatas){
            double fromValue = Double.valueOf(deviceData.getValueByAttributeCode(req.getFromAttr()));

            for(String code : req.getToAttr()){
                double toValue = 0;
                if (r.nextBoolean()) {
                    toValue = fromValue + req.getAddData() + r.nextDouble() * req.getRandomData();
                } else {
                    toValue = fromValue + req.getAddData() - r.nextDouble() * req.getRandomData();
                }
                deviceData.copyValueToAttribute(code,new BigDecimal(toValue).setScale(1,BigDecimal.ROUND_HALF_UP).toString());
            }
            newDeviceDatas.add(deviceData);
        }
        deviceDataService.updateBatchById(newDeviceDatas);
    }

    public static void main(String[] args) {
        System.out.println(R.random(-0,0));
    }

    public QueryDataHistoryAppResp queryDataHistoryApp(QueryDataHistoryAppReq req, HttpServletRequest request) throws CodeException{

        req.setStartTime(AsertUtils.asertToZeroHour(req.getStartTime()));
        req.setEndTime(AsertUtils.asertToNow(req.getEndTime()));

        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> attributeInfo = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());

        IPage<DeviceData> record =deviceDataService.page(
                new Page<>(req.getPageNum(),req.getPageSize()),
                new QueryWrapper<DeviceData>()
                        .eq(DeviceData.TASK_NUM, req.getTaskNum())
                        .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                        .between(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getStartTime()), TimeUtils.parseTime(req.getEndTime()))
                        .orderByDesc(DeviceData.DATA_TIME));

        List<QueryDataHistoryApp> datas = new ArrayList<>();
        for(DeviceData data : record.getRecords()){
            QueryDataHistoryApp app = new QueryDataHistoryApp();
            app.setTime(TimeUtils.formatTime(data.getDataTime()));
            for(AttributeEnum attributeEnum : AttributeEnum.values()){
                app.getAttributes().add(QueryDataHistoryValue.builder().attributeName(attributeInfo.get(attributeEnum.getValue()))
                        .attributeValue(data.getValueByAttributeCode(attributeEnum.getValue())).build());
            }
            datas.add(app);
        }

        return QueryDataHistoryAppResp.builder()
                .historyData(datas)
                .pageTotal(record.getTotal())
                .build();
    }


    public QueryDataRealtimeAppResp queryDataRealtimeApp(QueryDataRealtimeAppReq req, HttpServletRequest request) throws CodeException {
        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> attributeInfo = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());

        List<DeviceData> deviceDatas = deviceDataService.list(
                new QueryWrapper<DeviceData>()
                        .eq(DeviceData.TASK_NUM, req.getTaskNum())
                        .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                        .orderByDesc(DeviceData.DATA_TIME));
        if(deviceDatas.size() == 0){
            throw new CodeException(ResultCode.ERROR_NO_DATA);
        }
        DeviceData deviceData = deviceDatas.get(0);

        QueryDataRealtimeAppResp resp = new QueryDataRealtimeAppResp();
        resp.setTime(TimeUtils.formatTime(deviceData.getDataTime()));
        for(AttributeEnum attributeEnum : AttributeEnum.values()){
            resp.getAttributes().add(QueryDataHistoryValue.builder().attributeName(attributeInfo.get(attributeEnum.getValue()))
                    .attributeValue(deviceData.getValueByAttributeCode(attributeEnum.getValue())).build());
        }

        return resp;
    }
}
