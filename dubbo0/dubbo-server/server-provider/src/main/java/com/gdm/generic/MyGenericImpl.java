package com.gdm.generic;

public class MyGenericImpl implements MyGeneric{
    @Override
    public String sayMyHello(String name) {
        return "Hello" + name;
    }
}
