package com.github.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.constant.AttributeEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.QueryDataTable;
import com.github.managesystem.model.resp.QueryDataTableResp;
import com.github.managesystem.model.resp.ChartYData;
import com.github.managesystem.model.resp.QueryDataCharResp;
import com.github.managesystem.util.AsertUtils;
import com.github.managesystem.util.TimeUtils;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private ITaskService taskService;

    @Autowired
    private IDeviceDataService deviceDataService;


    public QueryDataCharResp queryDataChar(QueryDataTableReq req) throws CodeException{
        req.setStartTime(AsertUtils.asertToZeroHour(req.getStartTime()));
        req.setEndTime(AsertUtils.asertToNow(req.getEndTime()));
        req.setTaskNum(taskService.asertTaskNum(req.getTaskNum()));

        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());
        req.setDeviceNum(taskDevice.getDeviceNum());

        List<DeviceData> datas = getDeviceDataByCondition(req);

        return AttributeEnum.deviceDataToChart(datas,tableHeader);
    }

    private List<DeviceData> getDeviceDataByCondition(QueryDataTableReq req) {
        return deviceDataService.list(new QueryWrapper<DeviceData>()
                    .eq(DeviceData.TASK_NUM, req.getTaskNum())
                    .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                    .between(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getStartTime()), TimeUtils.parseTime(req.getEndTime()))
                    .orderByAsc(DeviceData.DATA_TIME));
    }


    public QueryDataTableResp queryDataTable(QueryDataTableReq req) throws CodeException{

        req.setStartTime(AsertUtils.asertToZeroHour(req.getStartTime()));
        req.setEndTime(AsertUtils.asertToNow(req.getEndTime()));
        req.setTaskNum(taskService.asertTaskNum(req.getTaskNum()));

        TaskDevice taskDevice = taskDeviceService.asertTaskDevice(req.getTaskNum(),req.getDeviceNum());
        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());
        req.setDeviceNum(taskDevice.getDeviceNum());

        List<DeviceData> datas = getDeviceDataByCondition(req);

        List<QueryDataTable> tableDatas = new ArrayList<>();
        for(DeviceData data : datas){
            tableDatas.add(QueryDataTable.builder()
                    .time(TimeUtils.formatTime(data.getDataTime()))
                    .values(AttributeEnum.deviceDataToMap(data)).build());
        }

        return QueryDataTableResp.builder()
                .tableHeader(tableHeader)
                .deviceNum(Arrays.asList(taskDevice.getTaskNum(),taskDevice.getDeviceNum()))
                .datas(tableDatas)
                .build();
    }

    public void changeData(ChangeDataReq req) {

        UpdateWrapper<DeviceData> wrapper = new UpdateWrapper<>();
        wrapper.eq(DeviceData.TASK_NUM, req.getTaskNum())
                .eq(DeviceData.DEVICE_NUM, req.getDeviceNum()).eq(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getTime()));
        for(Map.Entry<String,Double> entry : req.getValues().entrySet()){
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

        if(req.getListTemp().size() == 8){
            int timeSpace = req.getTimeSpace() * 60;
            //全部模拟
            int second = req.getRandomTime() * 60;
            List<DeviceData> deviceDatas = new LinkedList<>();
            long dataTime = startTime;
            while (dataTime< endTime){
                DeviceData deviceData = DeviceData.builder().taskNum(req.getTaskNum())
                        .deviceNum(req.getDeviceNum())
                        .dataTime(TimeUtils.parseTime(dataTime)).build();
                deviceDatas.add(createRandomData(req, startTime, stableTime, downTime, endTime, dataTime, deviceData));
                dataTime += timeSpace+ R.random(-second, second);

            }

            deviceDataService.remove(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM,req.getTaskNum())
                         .eq(DeviceData.DEVICE_NUM,req.getDeviceNum())
                         .between(DeviceData.DATA_TIME,TimeUtils.parseTime(req.getStartTime()),TimeUtils.parseTime(req.getEndTime())));

            deviceDataService.saveBatch(deviceDatas);

        }else {
            //部分模拟(时间不变)
            List<DeviceData> deviceDatas = deviceDataService.list(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM, req.getTaskNum())
                            .eq(DeviceData.DEVICE_NUM, req.getDeviceNum())
                            .between(DeviceData.DATA_TIME, TimeUtils.parseTime(req.getStartTime()), TimeUtils.parseTime(req.getEndTime()))
                            .orderByAsc(DeviceData.DATA_TIME));
            List<DeviceData> newDeviceDatas = new ArrayList<>();
            for(DeviceData deviceData : deviceDatas){
                long dataTime = deviceData.getDataTime().toEpochSecond(ZoneOffset.ofHours(8));
                newDeviceDatas.add(createRandomData(req, startTime, stableTime, downTime, endTime, dataTime, deviceData));
            }

            deviceDataService.updateBatchById(newDeviceDatas);
        }

    }

    private DeviceData createRandomData(SimulationDataReq req, Long startTime, Long stableTime, Long downTime, Long endTime, long dataTime, DeviceData deviceData) {
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
            if (new Random().nextBoolean()) {
                randomTemp = temp + new Random().nextDouble() * data.getRandomData();
            } else {
                randomTemp = temp - new Random().nextDouble() * data.getRandomData();
            }
            deviceData.copyValueToAttribute(data.getCode(), randomTemp);
        }
        return deviceData;
    }

    public void copyData(CopyDataReq req) {


    }

    public static void main(String[] args) {
        System.out.println(R.random(-120, 120));
    }

}
