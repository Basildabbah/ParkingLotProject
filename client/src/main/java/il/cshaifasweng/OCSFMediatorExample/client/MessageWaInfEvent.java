package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;

public class MessageWaInfEvent {
	private Message message;

	public Message getWarning() {
		return message;
	}

	public MessageWaInfEvent(Message message) {
		this.message = message;
	}
}
