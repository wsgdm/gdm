package com.gdm.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Start1 {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("META-INF/spring/dubbo-server1.xml");
        ac.start();
        System.in.read();
    }
}
