package com.gdm.generic;

import com.alibaba.dubbo.rpc.service.GenericService;
import com.gdm.dubbo.Hello;
import com.gdm.dubbo.MyHello;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestGeneric {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext ac =
                new ClassPathXmlApplicationContext("dubbo-client1.xml");
        GenericService gs = (GenericService) ac.getBean("Generic");
        String a = (String) gs.$invoke("sayMyHello",new String[]{"java.lang.String"},new Object[]{"haha"});
        System.out.println(a);

    }
}
