package org.pyf.feign.service;

import org.springframework.stereotype.Service;

/**
 * ${DESCRIPTION}
 * Created by hanqf on 2018/10/24 18:42.
 */

@Service
public class FeignServiceImpl implements IFeignService {

    @Override
    public String getInfo(String name) {
        return "hi,"+name+",sorry,error!";
    }
}
