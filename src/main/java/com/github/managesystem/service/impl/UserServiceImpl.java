package com.github.managesystem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.managesystem.entity.*;
import com.github.managesystem.mapper.UserMapper;
import com.github.managesystem.model.constant.RoleEnum;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.model.req.*;
import com.github.managesystem.model.resp.ListUserResp;
import com.github.managesystem.model.resp.UserInfoResp;
import com.github.managesystem.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.nutz.lang.Code;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
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

    @Autowired
    private ITaskService taskService;

    @Autowired
    private ITaskDeviceService taskDeviceService;

    @Autowired
    private IDeviceDataService deviceDataService;

    @Autowired
    private IAlarmLogService alarmLogService;

    @Autowired
    private IAlarmRuleService alarmRuleService;

    @Override
    public String login(LoginReq req) throws CodeException{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = this.getOne(queryWrapper.eq(User.LOGIN_NAME, req.getUserName()).eq(User.PASSWORD, req.getPassword()),false);
        if(Objects.isNull(user)){
            throw new CodeException(ResultCode.ERROR_USERNAME);
        }
        return user.getLoginName();
    }

    @Override
    public UserInfoResp userInfo(UserInfoReq req) throws CodeException{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        User user = this.getOne(queryWrapper.eq(User.LOGIN_NAME, req.getToken()),false);
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
        User user = this.getOne(new QueryWrapper<User>().eq(User.COMPANY_NAME, req.getCompanyName()),false);
        if(user == null) {
            List<Task> tasks = taskService.list(new QueryWrapper<Task>().eq(Task.COMPANY_NAME, req.getCompanyName()));
            for(Task task : tasks) {
                deviceDataService.remove(new QueryWrapper<DeviceData>().eq(DeviceData.TASK_NUM,task.getTaskNum()));
                alarmLogService.remove(new QueryWrapper<AlarmLog>().eq(AlarmLog.COMPANY_NAME,req.getCompanyName()));
                alarmRuleService.remove(new QueryWrapper<AlarmRule>().eq(AlarmLog.COMPANY_NAME,req.getCompanyName()));

            }
            taskDeviceService.remove(new QueryWrapper<TaskDevice>().eq(TaskDevice.COMPANY_NAME,req.getCompanyName()));
            taskService.remove(new QueryWrapper<Task>().eq(Task.COMPANY_NAME, req.getCompanyName()));
        }

    }

    @Override
    public void addUser(AddUserReq req) throws CodeException {
        User user = this.getOne(new QueryWrapper<User>().eq(User.LOGIN_NAME, req.getLoginName()), false);
        if(Objects.nonNull(user)){
            throw new CodeException(ResultCode.ERROR_USER_EXIST);
        }

        user = User.builder().companyName(req.getCompanyName())
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
        User user = this.getOne(new QueryWrapper<User>().eq(User.LOGIN_NAME, req.getLoginName()),false);
        user.setCompanyName(req.getCompanyName());
        user.setPassword(req.getPassword());
        user.setPhone(req.getPhone());
        user.setModifyTime(LocalDateTime.now());
        this.updateById(user);

    }
}
