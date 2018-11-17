package com.gdm.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        Connection conn = factory.createConnection();
        conn.start();
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue("gdm_queue");
        MessageConsumer mc =  session.createConsumer(queue);
        TextMessage tm = (TextMessage) mc.receive();
        String test = tm.getText();
        System.out.println(test);
        mc.close();
        session.close();
        conn.close();
    }
}
