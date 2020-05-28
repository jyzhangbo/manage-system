package com.github.managesystem.service;

import com.github.managesystem.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListDeviceAdminResp;
import com.github.managesystem.model.resp.ListDeviceUserResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface IDeviceService extends IService<Device> {


    ListDeviceAdminResp listDeviceAdmin(ListDeviceAdminReq req);

    void deleteDevice(DeleteDeviceReq req);

    void editDeviceAdmin(EditDeviceAdmin req);

    void addDevice(AddDeviceReq req);

    ListDeviceUserResp listDeviceUser(ListDeviceUserReq req);

    void editDeviceUser(EditDeviceUserReq req);
}
