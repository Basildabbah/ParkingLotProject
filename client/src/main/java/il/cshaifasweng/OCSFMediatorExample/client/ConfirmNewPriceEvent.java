package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ConfirmNewPriceEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public ConfirmNewPriceEvent(Message message) {
        this.message = message;
    }
}
