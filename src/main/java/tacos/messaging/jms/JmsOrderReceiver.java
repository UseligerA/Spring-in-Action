package tacos.messaging.jms;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;
import tacos.Order;

import javax.jms.Message;

@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;


    @Autowired
    public JmsOrderReceiver(JmsTemplate jms){
        this.jms = jms;
    }

    @SneakyThrows
    public Order receiveOrder() {

        return (Order)  jms.receiveAndConvert("tacoclooud.order.queue");
    }
}
