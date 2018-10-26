package org.pyf.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/24 18:38.
 */

@FeignClient(value = "eureka-provider",fallback = FeignServiceImpl.class)
public interface IFeignService {

    @RequestMapping(value = "/getinfo",method = RequestMethod.GET)
    String getInfo(@RequestParam(value = "name")String name);
}
