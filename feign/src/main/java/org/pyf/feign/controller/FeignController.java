package org.pyf.feign.controller;

import org.pyf.feign.service.IFeignService;
import org.pyf.oauth2.jwt.common.entity.JWT;
import org.pyf.oauth2.jwt.common.service.IOAuth2Service;
import org.pyf.oauth2.jwt.common.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Base64Utils;
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

    @Autowired
    private IOAuth2Service ioAuth2Service;

    @PreAuthorize("hasAuthority('USER01')")
    @GetMapping("/getinfo/{name}")
    public String getInfo(@PathVariable(name = "name") String name){
        System.out.println(UserUtils.getCurrentPrinciple());
        System.out.println(UserUtils.getCurrentAuthorities());
        System.out.println(UserUtils.getCurrentToken());
        return service.getInfo(name);
    }

    @GetMapping("/oauth/get_token")
    public JWT getToken(String username,String password){
        String authorization = "Basic " + Base64Utils.encodeToString("feign:passwd".getBytes()) ;
        JWT jwt = ioAuth2Service.getToken(authorization,"password",username,password,"");
        return jwt;
    }


    @GetMapping("/oauth/refresh_token")
    public JWT refreshToken(String refresh_token){
        String authorization = "Basic " + Base64Utils.encodeToString("feign:passwd".getBytes()) ;
        JWT jwt = ioAuth2Service.getToken(authorization,"refresh_token","","",refresh_token);
        return jwt;
    }
}
