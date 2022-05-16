package tacos.messaging.jms;

import tacos.Order;

public interface OrderReceiver {
    Order receiveOrder();
}
