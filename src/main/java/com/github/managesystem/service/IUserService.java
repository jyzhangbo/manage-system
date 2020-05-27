package com.github.managesystem.service;

import com.github.managesystem.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.UserInfoResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangbo
 * @since 2020-05-27
 */
public interface IUserService extends IService<User> {

    String login(LoginReq loginReq) throws CodeException;

    UserInfoResp userInfo(UserInfoReq req) throws CodeException;

    ListUserResp listUser(ListUserReq req) throws CodeException;

    void deleteUser(DeleteUserReq req);

    void addUser(AddUserReq req);

    void editUser(EditUserReq req);
}
