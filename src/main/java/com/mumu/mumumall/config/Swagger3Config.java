package com.mumu.mumumall.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class Swagger3Config {
    @Value("${swagger3.enable}")
    private boolean enable_swagger3;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(enable_swagger3)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mumu.mumumall.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MuMu-mall接口文档")
                .description("更多请咨询服务开发者Ray。")
                .contact(new Contact("MuMu。", "http://www.caowei.xyz", "piggy925@163 .com"))
                .version("1.0")
                .build();
    }
}