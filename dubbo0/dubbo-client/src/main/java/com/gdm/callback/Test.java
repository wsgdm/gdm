package com.gdm.callback;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("dubbo-client2.xml");
        CallBack cb = (CallBack) ac.getBean("callBack");
        cb.sayMyHello("111", new Listener() {
            @Override
            public void MyList(String msg) {
                System.out.println("222 +" + msg);
            }
        });
        System.in.read();
    }
}
