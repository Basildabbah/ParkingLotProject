package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class FullSubscriberOrder_enterEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public FullSubscriberOrder_enterEvent(Message message) {
        this.message = message;
    }
}
