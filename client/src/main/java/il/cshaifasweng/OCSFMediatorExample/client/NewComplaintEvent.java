package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
public class NewComplaintEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public NewComplaintEvent(Message message) {
        this.message = message;
    }
}
