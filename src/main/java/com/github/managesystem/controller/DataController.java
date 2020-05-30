package com.github.managesystem.controller;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.DataService;
import org.nutz.lang.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author:zhangbo
 * @Date:2020/5/21 14:52
 */
@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @PostMapping(value = "/simulation")
    public Result simulationData(@RequestBody SimulationDataReq req) throws CodeException{
        dataService.simulationData(req);
        return Result.ok();
    }

    @PostMapping(value = "/query/char")
    public Result queryDataChar(@RequestBody QueryDataTableReq req) throws CodeException {
        return Result.ok(dataService.queryDataChar(req));
    }

    @PostMapping(value = "/query/table")
    public Result queryDataTable(@RequestBody QueryDataTableReq req) throws CodeException {
        return Result.ok(dataService.queryDataTable(req));
    }

    @PostMapping(value = "/change")
    public Result changeData(@RequestBody ChangeDataReq req) throws CodeException {
        dataService.changeData(req);
        return Result.ok();
    }

    @PostMapping(value = "/copy")
    public Result copyData(@RequestBody CopyDataReq req) throws CodeException {
        dataService.copyData(req);
        return Result.ok();
    }

}
