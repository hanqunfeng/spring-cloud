package org.pyf.oauth2.jwt.common.service;

import org.pyf.oauth2.jwt.common.entity.JWT;
import org.springframework.stereotype.Component;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/30 12:40.
 */

@Component
public class OAuth2ServiceHystrix implements IOAuth2Service {


    public JWT getToken(String authorization, String type, String username, String password,String refresh_token) {
        return null;
    }


}
