package com.gdm.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("dubbo-client.xml");
        Hello hello = (Hello) ac.getBean("Ghello");
        String a = hello.sayHello("gdm");
        MyHello my = (MyHello) ac.getBean("GGhello");
        String b = my.sayMyHello("gdm");
        System.out.println(a);
        System.out.println(b);
    }
}
