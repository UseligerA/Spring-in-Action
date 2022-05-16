package tacos.messaging.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.messaging.KitchenUI;

@Component
public class RabbitOrderListener {
private KitchenUI ui;
@Autowired
    public RabbitOrderListener(KitchenUI kitchenUI){
    this.ui = kitchenUI;
}

@RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
ui.displayOrder(order);
}
}

