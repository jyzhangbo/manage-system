package com.github.managesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.config.interceptor.UserInterceptor;
import com.github.managesystem.entity.Device;
import com.github.managesystem.entity.Img;
import com.github.managesystem.entity.Task;
import com.github.managesystem.entity.User;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.ListCompanyNameReq;
import com.github.managesystem.model.req.ListImgReq;
import com.github.managesystem.model.req.ListTaskDeviceReq;
import com.github.managesystem.model.req.RemoveImgReq;
import com.github.managesystem.model.resp.DeviceInfo;
import com.github.managesystem.model.resp.ListTaskDeviceResp;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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

    public List<ListTaskDeviceResp> listTaskDevice(ListTaskDeviceReq req, HttpServletRequest request) throws CodeException {
        List<ListTaskDeviceResp> resp = new ArrayList<>();

        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.eq(Task.COMPANY_NAME,user.getCompanyName());
        }

        if(Objects.nonNull(req.getTaskState())){
            queryWrapper.eq(Task.TASK_STATUS,req.getTaskState());
        }

        List<Task> tasks = taskService.list(queryWrapper);
        if(tasks.size() == 0) {
            throw new CodeException(ResultCode.ERROR_TASK_NULL);
        }
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
                    .label(task.getTaskNum() + " / " + task.getTaskName())
                    .value(task.getTaskNum()).build());
        }

        return resp;
    }

    public List<Map<String,String>> listImg(ListImgReq req, HttpServletRequest request) {
        List<Map<String,String>> imgs = new ArrayList<>();

        User user = (User) request.getAttribute(UserInterceptor.USER_INFO);
        QueryWrapper<Img> queryWrapper = new QueryWrapper<>();
        if(!Strings.equals(user.getUserRole(),RoleEnum.ADMIN.value)){
            queryWrapper.in(Task.COMPANY_NAME,user.getCompanyName(),RoleEnum.ADMIN.value);
        }

        List<Img> list = imgService.list(queryWrapper);
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
