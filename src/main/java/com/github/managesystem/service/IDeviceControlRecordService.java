package com.github.managesystem.service;

import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.entity.DeviceControlRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-06-08
 */
public interface IDeviceControlRecordService extends IService<DeviceControlRecord> {

    List<DeviceControlRecord> getControlRecord(ProtocolDecodeOutData data);

}
