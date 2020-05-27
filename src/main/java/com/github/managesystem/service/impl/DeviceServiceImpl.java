package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.Device;
import com.github.managesystem.mapper.DeviceMapper;
import com.github.managesystem.model.req.AddDeviceReq;
import com.github.managesystem.model.req.DeleteDeviceReq;
import com.github.managesystem.model.req.EditDeviceAdmin;
import com.github.managesystem.model.req.ListDeviceAdminReq;
import com.github.managesystem.model.resp.AttributeInfo;
import com.github.managesystem.model.resp.ListDeviceAdminInfo;
import com.github.managesystem.model.resp.ListDeviceAdminResp;
import com.github.managesystem.service.IDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public ListDeviceAdminResp listDeviceAdmin(ListDeviceAdminReq req) {
        ListDeviceAdminResp resp = new ListDeviceAdminResp();
        Page<Device> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<Device> queryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(req.getCompanyName())){
            queryWrapper.eq(Device.COMPANY_NAME,req.getCompanyName());
        }
        if(Strings.isNotBlank(req.getDeviceNum())){
            queryWrapper.eq(Device.DEVICE_NUM,req.getDeviceNum());
        }

        IPage<Device> devices = this.page(page, queryWrapper);

        for(Device device : devices.getRecords()){
            resp.getInfos().add(ListDeviceAdminInfo.builder()
                    .companyName(device.getCompanyName())
                    .deviceName(device.getDeviceName())
                    .deviceNum(device.getDeviceNum())
                    .build());
        }
        resp.setTotal(devices.getTotal());
        return resp;
    }

    @Override
    public void deleteDevice(DeleteDeviceReq req) {
        this.remove(new QueryWrapper<Device>().eq(Device.DEVICE_NUM,req.getDeviceNum()));
    }

    @Override
    public void editDeviceAdmin(EditDeviceAdmin req) {
        Device device = this.getOne(new QueryWrapper<Device>().eq(Device.DEVICE_NUM, req.getDeviceNum()));
        device.setCompanyName(req.getCompanyName());
        device.setModifyTime(LocalDateTime.now());
        this.updateById(device);
    }

    @Override
    public void addDevice(AddDeviceReq req) {
        Device device = Device.builder().deviceNum(req.getDeviceNum())
                .deviceName(req.getDeviceNum())
                .companyName(req.getCompanyName())
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .collectSpace(30 * 60)
                .deviceImg("")
                .attributeInfo("")
                .build();

        this.save(device);

    }
}
