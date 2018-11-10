package com.gdm.dubbo;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class MyHelloImpl implements MyHello{
    @Override
    public String sayMyHello(String name) {
        return "MyHello" + name;
    }
}
