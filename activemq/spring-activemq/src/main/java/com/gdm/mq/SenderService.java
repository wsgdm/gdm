package com.gdm.mq;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import javax.jms.*;

@Service(value = "sender")
public class SenderService {

    @Resource(name = "jmsTemplate")
    private  JmsTemplate jmsTemplate;

    @Resource(name = "destinationQueue")
    private  Destination destination;

    public  void sendMesssge(){
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("nshaoma");
            }
        });
    }

    public  void recMessage() throws JMSException {
       TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
        //System.out.println(tm.getText());
    }
}

