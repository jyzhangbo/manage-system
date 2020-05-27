package com.github.managesystem.controller;

import com.github.managesystem.model.req.AddDeviceReq;
import com.github.managesystem.model.req.DeleteDeviceReq;
import com.github.managesystem.model.req.EditDeviceAdmin;
import com.github.managesystem.model.req.ListDeviceAdminReq;
import com.github.managesystem.model.resp.AttributeInfo;
import com.github.managesystem.model.resp.ListDeviceAdminInfo;
import com.github.managesystem.model.resp.ListDeviceAdminResp;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.IDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/18 18:48
 */
@RestController
@RequestMapping(value = "/device")
public class DeviceController {

    @Autowired
    private IDeviceService deviceService;

    @PostMapping(value = "/list/admin")
    public Result listDeviceAdmin(@RequestBody ListDeviceAdminReq req){
        return Result.ok(deviceService.listDeviceAdmin(req));
    }

    @PostMapping(value = "/delete")
    public Result deleteDevice(@RequestBody DeleteDeviceReq req){
        deviceService.deleteDevice(req);
        return Result.ok();
    }

    @PostMapping(value = "/add")
    public Result addDevice(@RequestBody AddDeviceReq req){
        deviceService.addDevice(req);
        return Result.ok();
    }

    @PostMapping(value = "/edit/admin")
    public Result editDeviceAdmin(@RequestBody EditDeviceAdmin req){
        deviceService.editDeviceAdmin(req);
        return Result.ok();
    }

}
