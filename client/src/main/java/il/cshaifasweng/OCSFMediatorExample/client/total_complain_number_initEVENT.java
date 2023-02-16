package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class total_complain_number_initEVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public total_complain_number_initEVENT(Message message) {
        this.message = message;
    }
}
