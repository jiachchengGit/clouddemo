package com.demo.cloud.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@FeignClient(value = "eureka-provider",fallback = FeignServiceHiHystric.class)
public interface FeignServiceHi {

    @RequestMapping(value = "/service/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
