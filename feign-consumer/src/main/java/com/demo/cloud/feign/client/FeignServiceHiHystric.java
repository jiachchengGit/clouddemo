package com.demo.cloud.feign.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@Component
public class FeignServiceHiHystric implements FeignServiceHi {

    @Override
    public String sayHiFromClientOne(@RequestParam(value = "name") String name) {
        return "Sorry for "+name;
    }
}
