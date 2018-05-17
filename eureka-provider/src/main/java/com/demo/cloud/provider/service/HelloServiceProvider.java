package com.demo.cloud.provider.service;

import com.demo.cloud.provider.client.ServiceHiFromFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chenjiacheng on 2018-05-07.
 */
@RestController
@RequestMapping("/service")
public class HelloServiceProvider {

    @Value("${server.port}")
    String port;

    @Value("${foo}")
    String foo;

    @Autowired
    private ServiceHiFromFeign serviceHiFromFeign;

    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port+",foo properties="+foo;
    }

}
