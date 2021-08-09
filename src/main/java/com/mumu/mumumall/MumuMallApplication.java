package com.mumu.mumumall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan(basePackages = {"com.mumu.mumumall.model.dao"})
@EnableCaching
public class MumuMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumuMallApplication.class, args);
    }

}
