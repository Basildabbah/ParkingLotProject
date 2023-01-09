package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
public class RefreshComplaintsEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public RefreshComplaintsEvent(Message message) {
        this.message = message;
    }
}
