package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class total_complain_numberEVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public total_complain_numberEVENT(Message message) {
        this.message = message;
    }
}
