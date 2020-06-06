package com.github.managesystem.config.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.managesystem.entity.User;
import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import com.github.managesystem.service.IUserService;
import com.github.managesystem.util.WebContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.Strings;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 网关token验证的拦截器.
 *
 * @Author:zhangbo
 * @Date:2018/5/15 17:37
 */
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private static final String USERNAME = "userName";

    public static final String USER_INFO = "USER_INFO";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        //获取请求头中的userName和time
        String userName = httpServletRequest.getHeader(USERNAME);

        if (Strings.isBlank(userName)) {
            throw new CodeException(ResultCode.ERROR_AUTHORITY_NOT_EXIST, USERNAME);
        }

        IUserService userService = WebContextUtils.findBean(IUserService.class);
        User user = userService.getOne(new QueryWrapper<User>().eq(User.LOGIN_NAME, userName),false);

        if(user == null){
            throw new CodeException(ResultCode.ERROR_AUTHORITY,userName);
        }

        httpServletRequest.setAttribute(USER_INFO, user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
