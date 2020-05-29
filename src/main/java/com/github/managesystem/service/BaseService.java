package com.github.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.Img;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.User;
import com.github.managesystem.model.req.ListCompanyNameReq;
import com.github.managesystem.model.req.ListImgReq;
import com.github.managesystem.model.req.ListTaskDeviceReq;
import com.github.managesystem.model.req.RemoveImgReq;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.model.resp.ListTaskDeviceResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:zhangbo
 * @Date:2020/5/28 16:11
 */
@Service
public class BaseService {

    @Autowired
    private IUserService userService;

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private IImgService imgService;


    public List<String> listCompanyName(ListCompanyNameReq req){
        List<String> companyNames = new ArrayList<>();
        List<User> users = userService.list();
        for (User user : users){
            if(!companyNames.contains(user.getCompanyName())){
                companyNames.add(user.getCompanyName());
            }
        }
        return companyNames;
    }

    public List<ListTaskDeviceResp> listTaskDevice(ListTaskDeviceReq req) {
        List<ListTaskDeviceResp> resp = new ArrayList<>();
        List<Task> tasks = taskService.list();
        for(Task task : tasks){
            List<DeviceInfo> devices = taskDeviceService.listDeviceByTaskNum(task.getTaskNum());
            List<Map<String,String>> children = new ArrayList<>();
            for(DeviceInfo deviceInfo : devices){
                Map<String,String> map = new HashMap<>();
                map.put("label",deviceInfo.getDeviceName());
                map.put("value",deviceInfo.getDeviceNum());
                children.add(map);
            }
            resp.add(ListTaskDeviceResp.builder().children(children)
                    .label(task.getTaskName())
                    .value(task.getTaskNum()).build());
        }

        return resp;
    }

    public List<Map<String,String>> listImg(ListImgReq req) {
        List<Map<String,String>> imgs = new ArrayList<>();
        List<Img> list = imgService.list();
        for(Img img : list){
            Map<String,String> map = new HashMap<>();
            map.put("name",img.getImgName());
            map.put("url",img.getImgUrl());
            imgs.add(map);
        }
        return imgs;
    }

    public void removeImg(RemoveImgReq req) {
        imgService.remove(new QueryWrapper<Img>().eq(Img.IMG_URL,req.getUrl()));
    }
}
