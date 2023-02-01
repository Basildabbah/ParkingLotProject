package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class CancelReserveEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public CancelReserveEvent(Message message) {
        this.message = message;
    }
}
