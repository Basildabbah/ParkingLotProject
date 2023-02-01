package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class OrdersReportEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public OrdersReportEvent(Message message) {
        this.message = message;
    }
}