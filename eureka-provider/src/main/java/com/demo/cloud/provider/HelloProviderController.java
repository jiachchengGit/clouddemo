package com.demo.cloud.provider;

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
@RequestMapping("/client")
public class HelloProviderController {

    @Autowired
    private ServiceHiFromFeign serviceHiFromFeign;

    @RequestMapping("/hiClient")
    public String hiClinet(@RequestParam String name) {
        return serviceHiFromFeign.sayHiFromClientTwo(name)+"###client";
    }
}
