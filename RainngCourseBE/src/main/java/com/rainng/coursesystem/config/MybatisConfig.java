package com.rainng.coursesystem.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.rainng.coursesystem.handle.mybatismethod.ExpandSqlInsertList;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan("com.rainng.coursesystem.dao.mapper")
@EnableTransactionManagement
public class MybatisConfig {

        @Bean
        public PaginationInterceptor paginationInterceptor() {
            return new PaginationInterceptor();
         }

    @Bean
    public ExpandSqlInsertList expandSqlInsertList() {
        return new ExpandSqlInsertList();
    }
}
