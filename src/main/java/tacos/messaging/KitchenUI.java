package tacos.messaging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tacos.Order;

@Component
@Slf4j
public class KitchenUI {
    public void displayOrder(Order order){
        // Можно сделать чуть больше, чем просто зарегистрировать полученный тако.
        // Чтобы отобразить его в каком-то пользовательском интерфейсе.
        log.info("RECEIVED ORDER:  " + order);
    }
}
