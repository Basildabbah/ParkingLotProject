package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class home_review_Event {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public home_review_Event(Message message) {
        this.message = message;
    }
}
