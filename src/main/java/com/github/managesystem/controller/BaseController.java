package com.github.managesystem.controller;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @PostMapping(value = "/list/task/device")
    public Result listTaskDevice(@RequestBody ListTaskDeviceReq req, HttpServletRequest request) throws CodeException {
        return Result.ok(baseService.listTaskDevice(req,request));
    }

    @PostMapping(value = "/list/img")
    public Result listImg(@RequestBody ListImgReq req, HttpServletRequest request){
        return Result.ok(baseService.listImg(req,request));
    }


    @PostMapping(value = "/remove/img")
    public Result removeImg(@RequestBody RemoveImgReq req){
        baseService.removeImg(req);
        return Result.ok();
    }



}
