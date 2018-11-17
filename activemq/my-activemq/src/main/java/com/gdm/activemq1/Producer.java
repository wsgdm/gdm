package com.gdm.activemq1;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.stomp.Stomp;

import javax.jms.*;
import java.io.IOException;

public class Producer {

    public static void main(String[] args) throws JMSException, IOException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        Connection connect = factory.createConnection();
        connect.start();
        Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("gdm_topic");
        MessageProducer mp = session.createProducer(topic);
        mp.setDeliveryMode(DeliveryMode.PERSISTENT);
        for(int i = 0; i < 5; i++){
            TextMessage tm = session.createTextMessage("44444");
            mp.send(tm);
        }
        System.in.read();
        mp.close();
        session.close();
        connect.close();
    }
}
