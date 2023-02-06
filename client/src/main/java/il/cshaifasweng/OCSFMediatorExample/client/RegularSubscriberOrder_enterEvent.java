package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RegularSubscriberOrder_enterEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public RegularSubscriberOrder_enterEvent(Message message) {
        this.message = message;
    }
}
