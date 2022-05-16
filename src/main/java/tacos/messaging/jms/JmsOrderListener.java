/*package tacos.messaging.kitchen.messaging.jms.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class JmsOrderListener {
private KitchenUI ui;
@Autowired
    public OrderListener(KitchenUI ui){
    this.ui = ui;
}

@JmsListener(destination = "tacocloud.order.queue")
    public void receiveOrder(Order order){
ui.displayOrder(order);
}
}
*/