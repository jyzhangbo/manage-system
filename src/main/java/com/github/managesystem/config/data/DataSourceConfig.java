package com.github.managesystem.config.data;

import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Author:zhangbo
 * @Date:2020/5/26 16:18
 */
@Configuration
public class DataSourceConfig {

    @Bean
    public Dao dao(DataSource dataSource) {
        return new NutDao(
                dataSource
        );
    }

}
