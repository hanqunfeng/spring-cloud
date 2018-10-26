package org.pyf.feign.controller;

import org.pyf.feign.service.IFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/24 18:01.
 */

@RestController
public class FeignController {

    @Autowired
    private IFeignService service;

    @GetMapping("getinfo/{name}")
    public String getInfo(@PathVariable(name = "name") String name){
        return service.getInfo(name);
    }
}
