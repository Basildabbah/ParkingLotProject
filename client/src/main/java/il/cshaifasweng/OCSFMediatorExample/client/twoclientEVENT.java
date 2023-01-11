package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class twoclientEVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public twoclientEVENT(Message message) {
        this.message = message;
    }
}
