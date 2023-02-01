package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class CheckReservationStatus {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public CheckReservationStatus(Message message) {
        this.message = message;
    }
}
