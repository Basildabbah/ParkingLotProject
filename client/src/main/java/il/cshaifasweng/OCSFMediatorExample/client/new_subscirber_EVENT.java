
package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class new_subscirber_EVENT {
    private Message message;

    public Message getWarning() {
        return message;
    }

    public new_subscirber_EVENT(Message message) {
        this.message = message;
    }
}
