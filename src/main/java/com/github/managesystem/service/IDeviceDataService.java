package com.github.managesystem.service;

import com.github.managesystem.collection.model.ProtocolDecodeOutData;
import com.github.managesystem.entity.DeviceControlRecord;
import com.github.managesystem.entity.DeviceData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-30
 */
public interface IDeviceDataService extends IService<DeviceData> {

    void putData(ProtocolDecodeOutData data);

}
