package com.demo.cloud.ribbon.web;

import com.demo.cloud.ribbon.service.RibbonHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@RestController
public class RibbonHelloControler {
    @Autowired
    RibbonHelloService helloService;

    @RequestMapping(value = "/hello")
    public String hi(@RequestParam String name){
        return helloService.hiService(name);
    }
}
