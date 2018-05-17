package com.demo.cloud.feign.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjiacheng on 2018-05-16.
 */
@RestController
@RequestMapping("/service")
public class TestServiceProvider {

    @RequestMapping(value = "/hiFromFeign",method = RequestMethod.GET)
    public String hiFromFeign(@RequestParam String name){
        return "say hi From TestServiceProvider.class----,"+name;
    }

}
