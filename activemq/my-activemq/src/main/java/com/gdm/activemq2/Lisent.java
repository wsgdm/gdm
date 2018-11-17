package com.gdm.activemq2;

import javax.jms.*;

public class Lisent implements MessageListener {


    @Override
    public void onMessage(Message message) {
        if(message instanceof TextMessage){
            TextMessage tm = (TextMessage) message;
            try {
                String text = tm.getText();
                System.out.println(text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if(message instanceof ObjectMessage){
            ObjectMessage om = (ObjectMessage) message;
            try {
                Object obj = om.getObject();
                System.out.println(obj);
            } catch (JMSException e) {
                e.printStackTrace();
            }

        }
    }
}
