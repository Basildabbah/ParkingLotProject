package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class loginadminEvent2 {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public loginadminEvent2(Message message) {
        this.message = message;
    }
}
