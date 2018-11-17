package com.gdm.mq1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("failover:(tcp://11.11.11.101:61616,tcp://11.11.11.101:61716,tcp://11.11.11.101:61816)?initialReconnectDelay=1000");
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
