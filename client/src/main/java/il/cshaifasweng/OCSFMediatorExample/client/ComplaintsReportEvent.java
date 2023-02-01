package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class ComplaintsReportEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public ComplaintsReportEvent(Message message) {
        this.message = message;
    }
}