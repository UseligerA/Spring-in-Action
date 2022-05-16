package tacos.messaging.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tacos.Order;
import tacos.messaging.KitchenUI;


@Component
@Slf4j
public class KafkaOrderListener {
private KitchenUI kitchenUI;

@Autowired

    public KafkaOrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(Order order, ConsumerRecord<String,Order> record){
    log.info("Received from partition {} with timestamp {}",record.partition(),record.timestamp());
    kitchenUI.displayOrder(order);
    }
}
