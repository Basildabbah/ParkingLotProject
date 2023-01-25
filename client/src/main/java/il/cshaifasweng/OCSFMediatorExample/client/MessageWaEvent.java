package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class MessageWaEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public MessageWaEvent(Message message) {
        this.message = message;
    }
}
