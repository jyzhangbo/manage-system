package com.github.managesystem.config;

import com.github.managesystem.config.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * mvc配置类
 *
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    /**
     * 解决跨域
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true).allowedHeaders("*").allowedMethods("*");
    }

    /**
     * 静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加网关token验证拦截器
        UserInterceptor gatewayInterceptor = new UserInterceptor();
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(gatewayInterceptor);
        interceptorRegistration.addPathPatterns("/**");
        interceptorRegistration.excludePathPatterns("/user/login");

    }

}
