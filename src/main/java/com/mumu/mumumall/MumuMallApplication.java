package com.mumu.mumumall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.mumu.mumumall.model.dao"})
public class MumuMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(MumuMallApplication.class, args);
    }

}
