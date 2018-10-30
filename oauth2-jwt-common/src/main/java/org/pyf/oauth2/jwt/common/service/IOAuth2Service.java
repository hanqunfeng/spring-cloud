package org.pyf.oauth2.jwt.common.service;

import org.pyf.oauth2.jwt.common.entity.JWT;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/30 12:37.
 */

@FeignClient(value = "oauth2-jwt",fallback = OAuth2ServiceHystrix.class )
public interface IOAuth2Service {

    @PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam(value = "username",required = false) String username, @RequestParam(value = "password",required = false) String password, @RequestParam(value = "refresh_token",required = false)String refresh_token);
}
