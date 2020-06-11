package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.managesystem.collection.handle.decoder.TemperatureDecoder;
import com.github.managesystem.collection.model.DeviceAttr;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.entity.DeviceData;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.mapper.DeviceDataMapper;
import com.github.managesystem.model.constant.TaskStateEnum;
import com.github.managesystem.service.IDeviceControlRecordService;
import com.github.managesystem.service.IDeviceDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.managesystem.service.ITaskDeviceService;
import com.github.managesystem.util.TimeUtils;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<DeviceControlRecord> putData(ProtocolDecodeOutData data) {
        DeviceData deviceData = DeviceData.builder().build();
        TaskDevice taskDevice = taskDeviceService.getOne(new QueryWrapper<TaskDevice>()
                .eq(TaskDevice.DEVICE_NUM, data.getDevNum())
                .eq(TaskDevice.TASK_STATUS,TaskStateEnum.START.value),false);
        if(taskDevice!= null){
            deviceData.setTaskNum(taskDevice.getTaskNum());
        }
        deviceData.setDeviceNum(data.getDevNum());

        for(DeviceAttr attr : data.getAttrs()){
            deviceData.setDataTime(TimeUtils.parseTime(attr.getTime()));
            if(Strings.equals(attr.getDataType(),TemperatureDecoder.uploadData)){
                for(Map.Entry<String,String> entry : attr.getData().entrySet()){
                    deviceData.copyValueToAttribute(entry.getKey(),Double.valueOf(entry.getValue()));
                }
            }
        }
        this.save(deviceData);

        List<DeviceControlRecord> records = deviceControlRecordService.list(new QueryWrapper<DeviceControlRecord>()
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum())
                .eq(DeviceControlRecord.CONTROL_STATE, 0));

        deviceControlRecordService.update(new UpdateWrapper<DeviceControlRecord>()
                .set(DeviceControlRecord.CONTROL_STATE, 1)
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum()));

        return records;
    }
}
