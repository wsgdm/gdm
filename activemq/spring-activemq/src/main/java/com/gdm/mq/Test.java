package com.gdm.mq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.JMSException;

public class Test {

    public static void main(String[] args) throws JMSException {

        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");

        SenderService ss = (SenderService) ac.getBean("sender");
        ss.recMessage();
    }
}
