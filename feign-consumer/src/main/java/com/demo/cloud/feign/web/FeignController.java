package com.demo.cloud.feign.web;

import com.demo.cloud.feign.client.FeignServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@RestController
@RequestMapping("/client")
public class FeignController {

    @Autowired
    FeignServiceHi feignServiceHi;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return feignServiceHi.sayHiFromClientOne(name);
    }
}
