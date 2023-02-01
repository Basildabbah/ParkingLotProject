package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class StatsEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public StatsEvent(Message message) {
        this.message = message;
    }
}