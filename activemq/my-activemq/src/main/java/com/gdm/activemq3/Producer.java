package com.gdm.activemq3;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        Connection conn = factory.createConnection();
        conn.start();
        Session session = conn.createSession(true,Session.AUTO_ACKNOWLEDGE);
        Queue queue =  session.createQueue("gdm_queue");
        MessageProducer producer = session.createProducer(queue);
        for(int i = 0; i < 5 ; i++){
            TextMessage tm = session.createTextMessage("nihao" + i);
            producer.send(tm);
            session.commit();
        }
        session.commit();
        producer.close();
        session.close();
        conn.close();

    }
}
