package com.gdm.dubbo;


import com.alibaba.dubbo.config.annotation.Service;

@Service
public class HelloImpl implements Hello {

    int count = 0;

    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Hello:" + name + (++count));
        return "Hello:" + name;
    }
}
