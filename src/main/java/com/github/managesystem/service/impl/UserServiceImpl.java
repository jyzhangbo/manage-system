package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.UserMapper;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.ListUserReq;
import com.github.managesystem.model.req.LoginReq;
import com.github.managesystem.model.req.UserInfoReq;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.UserInfoResp;
import com.github.managesystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public String login(LoginReq req) throws CodeException{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = this.getOne(queryWrapper.eq(User.LOGIN_NAME, req.getUsername()).eq(User.PASSWORD, req.getPassword()));
        if(Objects.isNull(user)){
            throw new CodeException(ResultCode.ERROR_USERNAME);
        }
        return user.getLoginName();
    }

    @Override
    public UserInfoResp userInfo(UserInfoReq req) throws CodeException{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = this.getOne(queryWrapper.eq(User.LOGIN_NAME, req.getToken()));
        if(Objects.isNull(user)){
            throw new CodeException(ResultCode.ERROR_USERNAME);
        }
        return UserInfoResp.builder()
                .roles(Arrays.asList(user.getUserRole()))
                .introduction("hi!")
                .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                .name(user.getLoginName()).build();
    }

    @Override
    public ListUserResp listUser(ListUserReq req) throws CodeException {


        return null;
    }
}
