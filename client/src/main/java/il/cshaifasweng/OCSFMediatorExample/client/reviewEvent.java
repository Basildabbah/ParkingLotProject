package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class reviewEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public reviewEvent(Message message) {
        this.message = message;
    }
}
