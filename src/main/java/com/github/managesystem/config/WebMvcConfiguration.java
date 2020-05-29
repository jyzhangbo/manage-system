package com.github.managesystem.config;

import com.github.managesystem.config.interceptor.UserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 配置类.
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurationSupport {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
    }

   /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加网关token验证拦截器
        UserInterceptor gatewayInterceptor = new UserInterceptor();
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(gatewayInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/user/login");
        interceptorRegistration.excludePathPatterns("/","/static/**");

    }

}
