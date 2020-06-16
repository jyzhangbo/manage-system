package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.managesystem.collection.handle.decoder.TemperatureDecoder;
import com.github.managesystem.collection.model.DeviceAttr;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.entity.*;
import com.github.managesystem.mapper.DeviceDataMapper;
import com.github.managesystem.model.constant.TaskStateEnum;
import com.github.managesystem.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.util.TimeUtils;
import lombok.AllArgsConstructor;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-30
 */
@Service
public class DeviceDataServiceImpl extends ServiceImpl<DeviceDataMapper, DeviceData> implements IDeviceDataService {

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private IDeviceControlRecordService deviceControlRecordService;

    @Autowired
    private IAlarmRuleService alarmRuleService;

    @Autowired
    private IAlarmLogService alarmLogService;

    @Override
    public List<DeviceControlRecord> putData(ProtocolDecodeOutData data) {

        TaskDevice taskDevice = taskDeviceService.getOne(new QueryWrapper<TaskDevice>()
                .eq(TaskDevice.DEVICE_NUM, data.getDevNum())
                .eq(TaskDevice.TASK_STATUS,TaskStateEnum.START.value),false);
        List<AlarmRule> rules = new ArrayList<>();
        if(taskDevice!=null) {
            rules = alarmRuleService.list(new QueryWrapper<AlarmRule>()
                    .eq(AlarmRule.COMPANY_NAME, taskDevice.getCompanyName())
                    .eq(AlarmRule.IS_DEL, 0).eq(AlarmRule.IS_ENABLE, 1));
        }

        List<DeviceData> deviceDatas = new ArrayList<>();
        for(DeviceAttr attr : data.getAttrs()){
            DeviceData deviceData = DeviceData.builder().build();
            deviceData.setDeviceNum(data.getDevNum());
            deviceData.setDataTime(TimeUtils.parseTime(attr.getTime()));
            if(Strings.equals(attr.getDataType(),TemperatureDecoder.uploadData)){
                for(Map.Entry<String,String> entry : attr.getData().entrySet()){
                    String key = entry.getKey();
                    double value = Double.valueOf(entry.getValue());
                    deviceData.copyValueToAttribute(key,value);

                    if(taskDevice!= null){
                        deviceData.setTaskNum(taskDevice.getTaskNum());
                        saveAlarmLog(rules,taskDevice,key,value);
                    }
                }
                deviceDatas.add(deviceData);
            }
        }

        this.saveBatch(deviceDatas);

        List<DeviceControlRecord> records = deviceControlRecordService.list(new QueryWrapper<DeviceControlRecord>()
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum())
                .eq(DeviceControlRecord.CONTROL_STATE, 0));

        deviceControlRecordService.update(new UpdateWrapper<DeviceControlRecord>()
                .set(DeviceControlRecord.CONTROL_STATE, 1)
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum()));

        return records;
    }


    public void saveAlarmLog(List<AlarmRule> rules,TaskDevice taskDevice,String key,double value){
        List<AlarmLog> logs= new ArrayList<>();

        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());

        if(rules.size() > 0){
            for(AlarmRule  rule : rules){
                if((Strings.equals(rule.getAlarmRuleObject(),"温度上限") && value > rule.getJudgeValue()) ||
                        (Strings.equals(rule.getAlarmRuleObject(),"温度下限") && value < rule.getJudgeValue()) ){
                    logs.add(AlarmLog.builder().alarmRuleObject(rule.getAlarmRuleObject())
                            .alarmRuleCondition(rule.getJudgeType())
                            .createTime(LocalDateTime.now())
                            .companyName(rule.getCompanyName())
                            .deviceNum(taskDevice.getDeviceNum())
                            .collectionPoint(tableHeader.get(key))
                            .modifyTime(LocalDateTime.now())
                            .build());
                }
            }
            alarmLogService.saveBatch(logs);
        }
    }
}
