package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListCompanyNameReq;
import com.github.managesystem.model.req.ListDeviceAdminReq;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 16:10
 */
@RestController
@RequestMapping(value = "/base")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @PostMapping(value = "/list/companyName")
    public Result listCompanyName(@RequestBody ListCompanyNameReq req){
        return Result.ok(baseService.listCompanyName(req));
    }

}