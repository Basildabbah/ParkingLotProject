package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class GuestOnSiteOrderEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public GuestOnSiteOrderEvent(Message message) {
        this.message = message;
    }
}
