package com.github.managesystem.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletRequest;

/**
 * 获取spring容器.
 * @Author:zhangbo
 * @Date:2018/5/15 18:20
 */
@Component
public class WebContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 获取applicationContext
     */
    private static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 从应用环境中获取Bean.
     *
     * @param classBean
     * @param <T>
     * @return
     */
    public static <T> T findBean(Class<T> classBean) {
        return getApplicationContext().getBean(classBean);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (WebContextUtils.applicationContext == null) {
            WebContextUtils.applicationContext = applicationContext;
        }
    }
}
