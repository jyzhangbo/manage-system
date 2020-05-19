package com.github.managesystem.controller;

import com.github.managesystem.model.req.ListUserReq;
import com.github.managesystem.model.req.LoginReq;
import com.github.managesystem.model.req.LogoutReq;
import com.github.managesystem.model.req.UserInfoReq;
import com.github.managesystem.model.resp.ListUserInfo;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.model.resp.UserInfoResp;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:zhangbo
 * @Date:2020/5/14 16:37
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @PostMapping(value = "/login")
    public Result<String> userLogin(@RequestBody LoginReq loginReq){
        System.out.println("login:"+loginReq.getUsername());
        return Result.ok("admin");
    }


    @PostMapping(value = "/info")
    public Result<UserInfoResp> userInfo(@RequestBody UserInfoReq userInfoReq){
        System.out.println("info:"+userInfoReq.getToken());
        UserInfoResp userInfoResp = new UserInfoResp();
        userInfoResp.setName("zhangbo");
        userInfoResp.setRoles(Arrays.asList("admin"));
        userInfoResp.setIntroduction("I am a admin!");
        userInfoResp.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return Result.ok(userInfoResp);
    }

    @PostMapping(value = "/logout")
    public Result userLogout(@RequestBody LogoutReq userInfoReq){
        System.out.println("logout:"+userInfoReq.getToken());
        return Result.ok();
    }

    @PostMapping(value = "/list")
    public Result listUser(@RequestBody ListUserReq req){
        List<ListUserInfo> datas = new ArrayList<>();
        datas.add(ListUserInfo.builder().companyName("自如").phone("1383838338").loginName("ziroom").password("123").build());
        datas.add(ListUserInfo.builder().companyName("贝壳").phone("1383838339").loginName("beike").password("123").build());
        return Result.ok(ListUserResp.builder().infos(datas).total(datas.size()).build());
    }

}
