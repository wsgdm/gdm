package com.gdm.dubbo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("dubbo-client.xml");
        Hello hello = (Hello) ac.getBean("Ghello");
        for(int i = 0; i < 200; i++){
            String a = hello.sayHello("gdm");
            System.out.println(a + "---------" +i);
        }
    }
}
