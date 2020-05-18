package com.github.managesystem.controller;

import com.github.managesystem.model.ListUserInfo;
import com.github.managesystem.model.ListUserReq;
import com.github.managesystem.model.ListUserResp;
import com.github.managesystem.model.Result;
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
    public Result listDevice(@RequestBody ListUserReq req){
        List<ListUserInfo> datas = new ArrayList<>();
        datas.add(ListUserInfo.builder().companyName("自如").phone("1383838338").loginName("ziroom").password("123").build());
        datas.add(ListUserInfo.builder().companyName("贝壳").phone("1383838339").loginName("beike").password("123").build());
        return Result.ok(ListUserResp.builder().infos(datas).total(datas.size()).build());
    }

}
