package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListDeviceReq;
import com.github.managesystem.model.resp.ListDeviceInfo;
import com.github.managesystem.model.resp.ListDeviceResp;
import com.github.managesystem.model.resp.Result;
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

    @PostMapping(value = "/list")
    public Result listDevice(@RequestBody ListDeviceReq req){
        List<ListDeviceInfo> datas = new ArrayList<>();
        datas.add(ListDeviceInfo.builder().companyName("自如").phone("1383838338").loginName("ziroom").deviceNum("123").build());
        datas.add(ListDeviceInfo.builder().companyName("贝壳").phone("1383838339").loginName("beike").deviceNum("456").build());
        return Result.ok(ListDeviceResp.builder().infos(datas).total(datas.size()).build());
    }

}
