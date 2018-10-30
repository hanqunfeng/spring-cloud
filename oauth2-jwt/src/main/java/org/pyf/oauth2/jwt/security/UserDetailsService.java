package org.pyf.oauth2.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/29 19:45.
 */


public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> resultAuths =  new ArrayList();
        addCustomAuthorities(resultAuths);
        String password = passwordEncoder.encode("123456");
        return new User("user01", password, true, true,
                true, true, resultAuths);
    }


    protected void addCustomAuthorities(List<SimpleGrantedAuthority> authorities) {
        if (authorities != null) {
            authorities.add(new SimpleGrantedAuthority("USER01"));
        }
    }
}
