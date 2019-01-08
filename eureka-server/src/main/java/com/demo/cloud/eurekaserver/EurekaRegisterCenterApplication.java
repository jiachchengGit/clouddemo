package com.demo.cloud.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@EnableEurekaServer
@SpringBootApplication
//@EnableDiscoveryClient
public class EurekaRegisterCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegisterCenterApplication.class, args);
    }
}