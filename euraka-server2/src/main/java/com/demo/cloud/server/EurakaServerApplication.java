package com.demo.cloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 2018/11/27 10:53
 * jiacheng
 */
@EnableEurekaServer
@SpringBootApplication
//@EnableDiscoveryClient
public class EurakaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakaServerApplication.class,args);
    }
}
