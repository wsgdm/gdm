package com.gdm.dubbo;

public class Mymock implements MyHello {

    @Override
    public String sayMyHello(String s) {
        return "mock:" + s;
    }
}
