package com.github.managesystem.controller;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.ListAlarmReq;
import com.github.managesystem.model.req.QueryDataTableReq;
import com.github.managesystem.model.req.SimulationDataReq;
import com.github.managesystem.model.resp.*;
import com.github.managesystem.service.DataService;
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
    public Result simulationData(@RequestBody SimulationDataReq req){
        List<String> xDatas = new ArrayList<>();
        xDatas.add("2020-05-18 00:00:00");
        xDatas.add("2020-05-19 00:00:00");
        xDatas.add("2020-05-20 00:00:00");
        xDatas.add("2020-05-21 00:00:00");
        List<SimulationData> yDatas = new ArrayList<>();
        yDatas.add(SimulationData.builder().name("T0").values(Arrays.asList(13.1,24.1,24.1,16.1)).build());
        yDatas.add(SimulationData.builder().name("T1").values(Arrays.asList(13.2,24.2,25.2,16.2)).build());
        yDatas.add(SimulationData.builder().name("T2").values(Arrays.asList(13.3,24.3,26.3,16.3)).build());
        yDatas.add(SimulationData.builder().name("T3").values(Arrays.asList(13.4,24.4,27.4,16.4)).build());
        return Result.ok(SimulationDataResp.builder().xDatas(xDatas).yDatas(yDatas).build());
    }

    @PostMapping(value = "/query/char")
    public Result queryDataChar(@RequestBody QueryDataTableReq req){

        return Result.ok(dataService.queryDataChar(req));
    }

    @PostMapping(value = "/query/table")
    public Result queryDataTable(@RequestBody QueryDataTableReq req) throws CodeException {

        return Result.ok(dataService.queryDataTable(req));
    }

}
