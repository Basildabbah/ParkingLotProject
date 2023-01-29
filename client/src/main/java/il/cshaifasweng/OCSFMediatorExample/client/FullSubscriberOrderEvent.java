package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class FullSubscriberOrderEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public FullSubscriberOrderEvent(Message message) {
        this.message = message;
    }
}
