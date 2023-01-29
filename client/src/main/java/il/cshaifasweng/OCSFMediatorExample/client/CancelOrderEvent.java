package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class CancelOrderEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public CancelOrderEvent(Message message) {
        this.message = message;
    }
}
