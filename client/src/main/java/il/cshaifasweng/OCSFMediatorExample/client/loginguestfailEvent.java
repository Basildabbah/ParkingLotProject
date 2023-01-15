package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class loginguestfailEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public loginguestfailEvent(Message message) {
        this.message = message;
    }
}

