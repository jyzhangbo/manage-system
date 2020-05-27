package com.github.managesystem.controller;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListUserInfo;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.Result;
import com.github.managesystem.model.resp.UserInfoResp;
import com.github.managesystem.service.IUserService;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/login")
    public Result<String> userLogin(@RequestBody LoginReq req) throws CodeException{
        return Result.ok(userService.login(req));
    }


    @PostMapping(value = "/info")
    public Result<UserInfoResp> userInfo(@RequestBody UserInfoReq req) throws CodeException {

        return Result.ok(userService.userInfo(req));
    }

    @PostMapping(value = "/logout")
    public Result userLogout(@RequestBody LogoutReq userInfoReq){
        System.out.println("logout:"+userInfoReq.getToken());
        return Result.ok();
    }

    @PostMapping(value = "/list")
    public Result listUser(@RequestBody ListUserReq req) throws CodeException{
        return Result.ok(userService.listUser(req));
    }

    @PostMapping(value = "/delete")
    public Result deleteUser(@RequestBody DeleteUserReq req) throws CodeException{
        userService.deleteUser(req);
        return Result.ok();
    }

    @PostMapping(value = "/add")
    public Result addUser(@RequestBody AddUserReq req) throws CodeException{
        userService.addUser(req);
        return Result.ok();
    }

    @PostMapping(value = "/edit")
    public Result editUser(@RequestBody EditUserReq req) throws CodeException{
        userService.editUser(req);
        return Result.ok();
    }

}
