package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
public class ResponseComplaintEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public ResponseComplaintEvent(Message message) {
        this.message = message;
    }
}
