package com.gdm.activemq1;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer2 {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        Connection connect = factory.createConnection();
        connect.setClientID("gmd_c02");
        connect.start();
        Session session = connect.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic("gdm_topic");
        MessageConsumer mp = session.createDurableSubscriber(topic,"gdm_topic_c02");
        TextMessage tm = (TextMessage) mp.receive();
        String text = tm.getText();
        System.out.println(text);
        mp.close();
        session.close();
        connect.close();
    }
}
