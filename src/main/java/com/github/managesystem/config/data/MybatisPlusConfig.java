package com.github.managesystem.config.data;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述:
 * mybaisPlus 分页插件
 *
 * @author Yangjy
 * @date 2018-03-26
 */
@Configuration
@MapperScan("com.github.managesystem.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}