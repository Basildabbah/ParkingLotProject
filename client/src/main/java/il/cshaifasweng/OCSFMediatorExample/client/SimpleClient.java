package il.cshaifasweng.OCSFMediatorExample.client;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import static il.cshaifasweng.OCSFMediatorExample.client.PrimaryController.setStr;

public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {

		Message message = (Message) msg;
		if(((Message) msg).getMessage().equals("showparkfun")) {
			EventBus.getDefault().post(new WarningEvent(message));
			setStr(message.getMessage());
		}
		if(((Message) msg).getMessage().equals("showpricefun")) {
			EventBus.getDefault().post(new PriceEvent(message));
			setStr(message.getMessage());
		}
	}

	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
