package com.github.managesystem.service.impl;

import com.github.managesystem.entity.Device;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements IDeviceService {

}
