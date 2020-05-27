package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.User;
import com.github.managesystem.mapper.UserMapper;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.UserInfoResp;
import com.github.managesystem.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Strings;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

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
        Page<User> page = new Page<>(req.getPageNum(),req.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(Strings.isNotBlank(req.getCompanyName())){
            queryWrapper.eq(User.COMPANY_NAME,req.getCompanyName());
        }
        if(Strings.isNotBlank(req.getLoginName())){
            queryWrapper.eq(User.LOGIN_NAME,req.getLoginName());
        }
        if(Strings.isNotBlank(req.getPhone())){
            queryWrapper.eq(User.PHONE,req.getPhone());
        }

        IPage<User> page1 = this.page(page, queryWrapper);

        return ListUserResp.builder().infos(page1.getRecords()).total(page1.getTotal()).build();
    }

    @Override
    public void deleteUser(DeleteUserReq req) {
        this.remove(new QueryWrapper<User>().eq(User.LOGIN_NAME,req.getLoginName()));
    }

    @Override
    public void addUser(AddUserReq req) {
        User user = User.builder().companyName(req.getCompanyName())
                .companyNum(UUID.fromString(req.getCompanyName()).toString())
                .createTime(LocalDateTime.now())
                .modifyTime(LocalDateTime.now())
                .loginName(req.getLoginName())
                .password(req.getPassword())
                .phone(req.getPhone())
                .userRole(req.isAdmin() ? RoleEnum.USERADMIN.value : RoleEnum.USER.value)
                .build();

        this.save(user);
    }

    @Override
    public void editUser(EditUserReq req) {
        User user = this.getOne(new QueryWrapper<User>().eq(User.LOGIN_NAME, req.getLoginName()));
        user.setCompanyName(req.getCompanyName());
        user.setCompanyNum(UUID.fromString(req.getCompanyName()).toString());
        user.setPassword(req.getPassword());
        user.setPhone(req.getPhone());
        user.setModifyTime(LocalDateTime.now());
        this.updateById(user);

    }
}
