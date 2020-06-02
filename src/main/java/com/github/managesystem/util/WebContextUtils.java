package com.github.managesystem.util;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;

/**
 * 获取spring容器.
 * @Author:zhangbo
 * @Date:2018/5/15 18:20
 */
public class WebContextUtils {

    /**
     * 获取Spring应用上下文.
     *
     * @param request
     * @return
     */
    public static WebApplicationContext getWebAppliationContext(ServletRequest request) {
        return WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    }

    /**
     * 从应用环境中获取Bean.
     *
     * @param request
     * @param classBean
     * @param <T>
     * @return
     */
    public static <T> T findBean(ServletRequest request, Class<T> classBean) {
        WebApplicationContext context = getWebAppliationContext(request);
        return context.getBean(classBean);
    }

}
