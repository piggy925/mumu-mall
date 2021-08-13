package com.mumu.mumumall.config;

import com.mumu.mumumall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UserFilter过滤器配置
 */
@Configuration
public class UserFilterConfig {
    @Bean(name = "userFilterConf")
    public FilterRegistrationBean userFilterConfig() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(userFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/order/*");
        filterRegistrationBean.setName("userFilterConf");
        return filterRegistrationBean;
    }

    @Bean
    public UserFilter userFilter() {
        return new UserFilter();
    }
}
