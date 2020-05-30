package com.github.managesystem.config.interceptor;

import com.github.managesystem.model.exception.CodeException;
import com.github.managesystem.model.exception.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.nutz.lang.Lang;
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

    public static final String TIME = "time";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }

        //获取请求头中的userName和time
        String userName = httpServletRequest.getHeader(USERNAME);
        String time = httpServletRequest.getHeader(TIME);

       // log.info(Lang.getIP(httpServletRequest) + "@" + httpServletRequest.getRequestURI() + "请求头userName：{};time: {}", userName, time);

        if (Strings.isBlank(userName)) {
            throw new CodeException(ResultCode.ERROR_AUTHORITY, USERNAME);
        }

        httpServletRequest.setAttribute(USERNAME, userName);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
