package com.gdm.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Start {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("META-INF/spring/dubbo-server.xml");
        ac.start();
        System.in.read();
    }
}
