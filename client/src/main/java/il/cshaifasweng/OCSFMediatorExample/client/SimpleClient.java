package il.cshaifasweng.OCSFMediatorExample.client;

import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import static il.cshaifasweng.OCSFMediatorExample.client.loginadmin.setStr;


public class SimpleClient extends AbstractClient {
	
	private static SimpleClient client = null;

	SimpleClient(String host, int port) {
		super(host, port);
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		Message message = (Message) msg;
		if(((Message) msg).getMessage().equals("loginadmin")) {
			EventBus.getDefault().post(new WarningEvent(message));
		}
		if(((Message) msg).getMessage().equals("loginsubscriber")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("admin_forgetpass")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("newpassadmin")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("showpricefun")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("test")) {
			System.out.println("h");
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		else

		{
		}
	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 3000);
		}
		return client;
	}

}
