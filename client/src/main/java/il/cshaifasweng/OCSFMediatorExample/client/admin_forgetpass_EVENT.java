package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class admin_forgetpass_EVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public admin_forgetpass_EVENT(Message message) {
        this.message = message;
    }
}
