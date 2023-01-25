package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class showPriceManagerEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public showPriceManagerEvent(Message message) {
        this.message = message;
    }
}
