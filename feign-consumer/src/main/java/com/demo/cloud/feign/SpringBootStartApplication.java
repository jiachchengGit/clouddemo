package com.demo.cloud.feign;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * 在部署到外部tomcat使用
 * Created by chenjiacheng on 2018-05-14.
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FeignConsumerApplication.class);
    }
}
