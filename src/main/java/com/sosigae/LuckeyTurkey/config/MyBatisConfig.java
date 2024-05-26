package com.sosigae.LuckeyTurkey.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.sosigae.LuckeyTurkey.dao.mybatis.mapper")
public class MyBatisConfig {
    // 
}