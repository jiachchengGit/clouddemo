package com.demo.cloud.provider.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by chenjiacheng on 2018-05-16.
 */
@FeignClient(value = "feign-consumer",fallback = ServiceHiFromFeignHystric.class)
public interface ServiceHiFromFeign {
    @RequestMapping(value = "/service/hiFromFeign",method = RequestMethod.GET)
    String sayHiFromClientTwo(@RequestParam(value = "name") String name);
}
