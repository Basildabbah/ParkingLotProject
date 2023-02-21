package il.cshaifasweng.OCSFMediatorExample.client;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class checkifneedtorenewsubs {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public checkifneedtorenewsubs(Message message) {
        this.message = message;
    }
}
