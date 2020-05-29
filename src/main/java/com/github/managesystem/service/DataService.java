package com.github.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.TaskDevice;
import com.github.managesystem.model.req.QueryDataTableReq;
import com.github.managesystem.model.resp.QueryDataTable;
import com.github.managesystem.model.resp.QueryDataTableResp;
import com.github.managesystem.model.resp.SimulationData;
import com.github.managesystem.model.resp.SimulationDataResp;
import org.nutz.json.Json;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author:zhangbo
 * @Date:2020/5/29 14:58
 */
@Service
public class DataService {

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private ITaskService taskService;


    public SimulationDataResp queryDataChar(QueryDataTableReq req){
        List<String> xDatas = new ArrayList<>();
        xDatas.add("2020-05-18 00:00:00");
        xDatas.add("2020-05-19 00:00:00");
        xDatas.add("2020-05-20 00:00:00");
        xDatas.add("2020-05-21 00:00:00");
        List<SimulationData> yDatas = new ArrayList<>();
        yDatas.add(SimulationData.builder().name("T0").values(Arrays.asList(23.1,24.1,25.1,26.1)).build());
        yDatas.add(SimulationData.builder().name("T1").values(Arrays.asList(23.2,24.2,25.2,26.2)).build());
        yDatas.add(SimulationData.builder().name("T2").values(Arrays.asList(23.3,24.3,25.3,26.3)).build());
        yDatas.add(SimulationData.builder().name("T3").values(Arrays.asList(23.4,24.4,25.4,26.4)).build());

        return SimulationDataResp.builder().xDatas(xDatas).yDatas(yDatas).build();
    }


    public QueryDataTableResp queryDataTable(QueryDataTableReq req) {

        if(Strings.isBlank(req.getTaskNum())){
            Task task = taskService.getOne(new QueryWrapper<Task>().orderByDesc(Task.MODIFY_TIME), false);
            req.setTaskNum(task.getTaskNum());
        }

        TaskDevice taskDevice = taskDeviceService.getOne(new QueryWrapper<TaskDevice>().eq(TaskDevice.TASK_NUM, req.getTaskNum())
                .eq(Strings.isNotBlank(req.getDeviceNum()),TaskDevice.DEVICE_NUM, req.getDeviceNum()), false);
        Map<String,String> tableHeader = Json.fromJsonAsMap(String.class,taskDevice.getAttributeInfo());

        Map<String,Double> map1 = new HashMap<>();
        map1.put("T1",23.5);
        map1.put("T2",24.5);
        map1.put("T3",25.5);
        map1.put("T4",21.5);
        map1.put("T5",21.5);
        map1.put("T6",21.5);
        map1.put("T7",21.5);
        map1.put("T8",21.5);
        QueryDataTable data1 = QueryDataTable.builder().time("2020-05-24").values(map1).build();
        QueryDataTable data2 = QueryDataTable.builder().time("2020-05-24").values(map1).build();
        return QueryDataTableResp.builder()
                .tableHeader(tableHeader)
                .deviceNum(Arrays.asList(taskDevice.getTaskNum(),taskDevice.getDeviceNum()))
                .datas(Arrays.asList(data1,data2))
                .build();
    }

}
