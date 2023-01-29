package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class GuestPreOrderEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public GuestPreOrderEvent(Message message) {
        this.message = message;
    }
}
