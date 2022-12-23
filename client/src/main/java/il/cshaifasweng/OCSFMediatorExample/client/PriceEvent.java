package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class PriceEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public PriceEvent(Message message) {
        this.message = message;
    }
}
