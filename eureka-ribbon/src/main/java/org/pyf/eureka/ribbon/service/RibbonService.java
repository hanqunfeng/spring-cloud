package org.pyf.eureka.ribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/24 17:57.
 */

@Service
public class RibbonService {

    @Autowired
    RestTemplate restTemplate;

    //配置断路器回调方法，一旦执行方法异常则会执行回调方法
    @HystrixCommand(fallbackMethod = "getInfoError")
    public String getInfo(String name) {
        return restTemplate.getForObject("http://EUREKA-PROVIDER/getinfo?name="+name,String.class);
    }

    public String getInfoError(String name){
        return "hi,"+name+",sorry,error!";
    }
}
