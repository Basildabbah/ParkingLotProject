package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class InvalidSpotsReportEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public InvalidSpotsReportEvent(Message message) {
        this.message = message;
    }
}