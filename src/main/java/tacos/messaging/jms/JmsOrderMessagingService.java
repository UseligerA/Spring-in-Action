package tacos.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Service;
import tacos.Order;
import tacos.messaging.OrderMessagingService;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;


@Service
public class JmsOrderMessagingService implements OrderMessagingService {
    private JmsTemplate jms;
    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms){
        this.jms = jms;
    }

    @Override
    public void sendOrder(Order order) {
        jms.convertAndSend("tacocloud.order.queue",order, message -> { message.setStringProperty("X_ORDER_SOURCE","WEB");
        return message;
        });
        //jms.send(session ->  session.createObjectMessage(order));
        }
        
}
