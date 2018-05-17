package com.demo.cloud.provider.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenjiacheng on 2018-05-16.
 */
@Component
public class ServiceHiFromFeignHystric implements ServiceHiFromFeign {
    @Override
    public String sayHiFromClientTwo(@RequestParam(value = "name") String name) {
        return "Sorry call from feign consumer error,"+name;
    }
}
