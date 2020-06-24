package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.mapper.DeviceControlRecordMapper;
import com.github.managesystem.service.IDeviceControlRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-06-08
 */
@Service
public class DeviceControlRecordServiceImpl extends ServiceImpl<DeviceControlRecordMapper, DeviceControlRecord> implements IDeviceControlRecordService {

    @Override
    public List<DeviceControlRecord> getControlRecord(ProtocolDecodeOutData data) {
        List<DeviceControlRecord> records = this.list(new QueryWrapper<DeviceControlRecord>()
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum())
                .eq(DeviceControlRecord.CONTROL_STATE, 0));

        this.update(new UpdateWrapper<DeviceControlRecord>()
                .set(DeviceControlRecord.CONTROL_STATE, 1)
                .eq(DeviceControlRecord.DEVICE_NUM, data.getDevNum()));

        return records;
    }
}
