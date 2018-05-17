package com.demo.cloud.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ZullServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZullServerApplication.class, args);
    }
}
