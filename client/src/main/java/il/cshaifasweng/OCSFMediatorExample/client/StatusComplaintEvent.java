package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
public class StatusComplaintEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public StatusComplaintEvent(Message message) {
        this.message = message;
    }
}
