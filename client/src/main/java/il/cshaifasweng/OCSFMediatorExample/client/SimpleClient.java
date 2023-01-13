package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Complaint;
import org.greenrobot.eventbus.EventBus;

import il.cshaifasweng.OCSFMediatorExample.client.ocsf.AbstractClient;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;

import java.util.ArrayList;

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
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("loginsubscriber")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("admin_forgetpass")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("id is used before")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("email is used before")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("2clients")) {
			EventBus.getDefault().post(new twoclientEVENT(message));
		}
		if(((Message) msg).getMessage().equals("newpassadmin")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("showpricefun")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("yes")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("#caralreadylinked")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("test")) {
			System.out.println("h");
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("add compliant succ")) {
			EventBus.getDefault().post(new NewComplaintEvent(message));
		}
		if(message.getMessage().equals("complaints")) {
			EventBus.getDefault().post(new StatusComplaintEvent(message));
		}
		if(message.getMessage().equals("allComplaints")) {
			EventBus.getDefault().post(new ResponseComplaintEvent(message));
		}
		if(message.getMessage().equals("2allComplaints")) {
			EventBus.getDefault().post(new RefreshComplaintsEvent(message));
		}
		if(message.getMessage().equals("loginguesttrue")) {
			EventBus.getDefault().post(new logingusetsuccEvent(message));
		}
		if(message.getMessage().equals("loginguestfail")) {
			EventBus.getDefault().post(new loginguestfailEvent(message));
		}


	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 6666);
		}
		return client;
	}

}
