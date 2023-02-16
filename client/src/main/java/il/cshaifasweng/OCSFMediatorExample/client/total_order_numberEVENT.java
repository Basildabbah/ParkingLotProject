package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class total_order_numberEVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public total_order_numberEVENT(Message message) {
        this.message = message;
    }
}
