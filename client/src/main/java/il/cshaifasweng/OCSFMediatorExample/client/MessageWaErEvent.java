package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class MessageWaErEvent {
	private Message message;

	public Message getWarning() {
		return message;
	}

	public MessageWaErEvent(Message message) {
		this.message = message;
	}
}
