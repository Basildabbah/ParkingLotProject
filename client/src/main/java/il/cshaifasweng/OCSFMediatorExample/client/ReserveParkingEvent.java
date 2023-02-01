package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
public class ReserveParkingEvent {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public ReserveParkingEvent(Message message) {
        this.message = message;
    }
}
