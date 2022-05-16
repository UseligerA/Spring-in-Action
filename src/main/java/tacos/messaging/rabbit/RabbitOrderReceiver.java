package tacos.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.messaging.jms.OrderReceiver;

@Component
public class RabbitOrderReceiver implements OrderReceiver {
 private RabbitTemplate rabbit;
 private MessageConverter converter;

 

 @Autowired
 public RabbitOrderReceiver(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
        this.converter = rabbit.getMessageConverter();
    }
    public Order receiveOrder(){
        return (Order) rabbit.receiveAndConvert("tacocloud.orders",
                new ParameterizedTypeReference<Order>(){});
    }
}
