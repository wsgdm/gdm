package com.gdm.activemq2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {

    public static void main(String[] args) throws JMSException, IOException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        factory.setTrustAllPackages(true);
        Connection conn = factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("gdm_queue");
        MessageConsumer mc =  session.createConsumer(queue);
        mc.setMessageListener(new Lisent());
//        ObjectMessage tm = (ObjectMessage) mc.receive();
//        String test = tm.getObject().toString();
//        System.out.println(test);
        System.in.read();
        mc.close();
        session.close();
        conn.close();
    }
}
