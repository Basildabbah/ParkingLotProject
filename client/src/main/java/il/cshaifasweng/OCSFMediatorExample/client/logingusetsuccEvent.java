package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class logingusetsuccEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public logingusetsuccEvent(Message message) {
        this.message = message;
    }
}
