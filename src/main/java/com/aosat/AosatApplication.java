package com.aosat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan(value = "com.aosat.Dao")
public class AosatApplication {

    public static void main(String[] args) {
        SpringApplication.run(AosatApplication.class, args);
    }

}
