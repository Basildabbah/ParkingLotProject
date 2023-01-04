package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class loginadminEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public loginadminEvent(Message message) {
        this.message = message;
    }
}
