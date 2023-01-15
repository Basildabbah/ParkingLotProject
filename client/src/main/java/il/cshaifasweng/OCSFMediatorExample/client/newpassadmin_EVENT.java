package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class newpassadmin_EVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public newpassadmin_EVENT(Message message) {
        this.message = message;
    }
}
