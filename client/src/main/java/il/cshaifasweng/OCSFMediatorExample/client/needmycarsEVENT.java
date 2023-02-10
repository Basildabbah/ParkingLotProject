package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class needmycarsEVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public needmycarsEVENT(Message message) {
        this.message = message;
    }
}
