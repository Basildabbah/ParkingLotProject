package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class RegularSubscriberOrderEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public RegularSubscriberOrderEvent(Message message) {
        this.message = message;
    }
}
