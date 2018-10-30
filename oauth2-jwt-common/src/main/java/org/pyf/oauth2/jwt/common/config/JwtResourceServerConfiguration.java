package org.pyf.oauth2.jwt.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/30 10:53.
 */


@Configuration
@EnableResourceServer
@ComponentScan(value = "org.pyf.oauth2.jwt.common.service")
@EnableFeignClients(basePackages = {"org.pyf.oauth2.jwt.common.service"})
public class JwtResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    Logger log = LoggerFactory.getLogger(JwtResourceServerConfiguration.class);


    @Value("${pub_jwt_key:pyf-pub-jwt.key}")
    String pub_jwt_key;
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {

        System.out.println("Created JwtTokenStore");
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer() {
        JwtAccessTokenConverter converter =  new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource(pub_jwt_key);
        String publicKey ;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Value("${jwt_regex_matchers:/oauth/get_token*,/oauth/refresh_token*,/actuator/**,.*swagger.*}")
    String jwt_regex_matchers;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        String[] jwt_regex_matchers_array = jwt_regex_matchers.split(",");
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(jwt_regex_matchers_array).permitAll()
                //.regexMatchers(jwt_regex_matchers.split(",")).permitAll()//基于正则匹配，不需要限制,没好使
                .antMatchers("/**").authenticated();//只要登录就可以访问

    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        log.info("Configuring ResourceServerSecurityConfigurer ");
        //resources.resourceId("feign").tokenStore(tokenStore); //注册资源服务名称，这个和eureka-server里注册的服务名称不是一回事
        resources.tokenStore(tokenStore());
    }

}
