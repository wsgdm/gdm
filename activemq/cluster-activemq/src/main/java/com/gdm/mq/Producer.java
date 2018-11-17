package com.gdm.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {

        ConnectionFactory factory = new ActiveMQConnectionFactory("failover:(tcp://11.11.11.101:61616,tcp://11.11.11.101:61716)?randomize=true");
        Connection conn = factory.createConnection();
        conn.start();
        Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue =  session.createQueue("gdm_queue");
        MessageProducer producer = session.createProducer(queue);
        for(int i = 0; i < 10; i++){
            TextMessage tm = session.createTextMessage("nihao" + i);
            producer.send(tm);
        }

        producer.close();
        session.close();
        conn.close();

    }
}
