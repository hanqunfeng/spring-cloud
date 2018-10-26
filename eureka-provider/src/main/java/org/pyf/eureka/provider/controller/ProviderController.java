package org.pyf.eureka.provider.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/24 17:54.
 */

@RestController
public class ProviderController {
    @Value("${server.port}")
    private String port;


    @ApiOperation(value = "获取服务器信息",notes = "根据传入名称打印服务器信息")
    @GetMapping("/getinfo")
    public String getInfo(String name){
        return "Hi,"+name+" , port is:" + port;
    }

    @ApiOperation(value = "获取服务器信息2",notes = "根据传入名称打印服务器信息2")
    @GetMapping("/getinfo2/{username}")
    public String getInfo2(@PathVariable(value = "username") String username){
        return "Hi,"+username+" , port is:" + port;
    }

}
