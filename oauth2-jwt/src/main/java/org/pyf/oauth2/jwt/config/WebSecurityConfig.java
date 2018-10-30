package org.pyf.oauth2.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/29 17:19.
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //为了测试简便，这里使用内存用户
        //auth.inMemoryAuthentication()
        //        .withUser("user01").password(bCryptPasswordEncoder().encode("123456")).roles("USER01","MANAGER01")
        //        .and()
        //        .withUser("user02").password(bCryptPasswordEncoder().encode("654321")).roles("USER02")
        //        .and().passwordEncoder(bCryptPasswordEncoder());

        //如果自定义UserDetailService，方式如下
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    /**
     * 必须声明UserDetailsService，否则refresh_token不可用
     * @return
     */
    @Bean
    public org.springframework.security.core.userdetails.UserDetailsService userDetailsService(){
       //return new UserDetailsService();

        //使用内存用户，与上面的简易配置效果一致，这里只是为了声明UserDetailsService
        List<SimpleGrantedAuthority> resultAuths01 =  new ArrayList();
        resultAuths01.add(new SimpleGrantedAuthority("USER01"));
        resultAuths01.add(new SimpleGrantedAuthority("MANAGER01"));
        User user01 = new User("user01",bCryptPasswordEncoder().encode("123456"),resultAuths01);

        List<SimpleGrantedAuthority> resultAuths02 =  new ArrayList();
        resultAuths02.add(new SimpleGrantedAuthority("USER02"));
        User user02 = new User("user02",bCryptPasswordEncoder().encode("654321"),resultAuths02);


        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user01,user02);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder(){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
        return passwordEncoder;
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //CSRF:因为不再依赖于Cookie，所以你就不需要考虑对CSRF（跨站请求伪造）的防范。
                .exceptionHandling().authenticationEntryPoint((request,response, authException)->response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**").authenticated() //所有请求都需要认证
                .and()
                .httpBasic();
    }





}
