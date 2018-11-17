package com.gdm.activemq2;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("tcp://11.11.11.101:61616");
        factory.setTrustAllPackages(true);
        Connection conn = factory.createConnection();
        conn.start();
        Session session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Queue queue =  session.createQueue("gdm_queue");
        MessageProducer producer = session.createProducer(queue);
        Person p = new Person();
        p.setAge(2);
        p.setName("gdm");
        ObjectMessage om = session.createObjectMessage(p);
        producer.send(om);
        producer.close();
        session.close();
        conn.close();

    }
}
