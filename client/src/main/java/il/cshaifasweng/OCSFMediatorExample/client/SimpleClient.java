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
		//*************************************************************************************
		//*************************************************************************************
		if(((Message) msg).getMessage().equals("#returnNewPrice") || ((Message) msg).getMessage().equals("#returnOldPrice")) {
			System.out.println("456");
			EventBus.getDefault().post(new ConfirmNewPriceEvent(message));
		}
		if(((Message) msg).getMessage().equals("showpricefunManager")) {
			EventBus.getDefault().post(new showPriceManagerEvent(message));
		}
		//*************************************************************************************
		//*************************************************************************************
		if(((Message) msg).getMessage().equals("loginadmin")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("loginsubscriber")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("admin_forgetpass")) {
			EventBus.getDefault().post(new admin_forgetpass_EVENT(message));
		}
		if(((Message) msg).getMessage().equals("id is used before")) {
			EventBus.getDefault().post(new new_subscirber_EVENT(message));
		}
		if(((Message) msg).getMessage().equals("email is used before")) {

			EventBus.getDefault().post(new new_subscirber_EVENT(message));
		}
		if(((Message) msg).getMessage().equals("2clients")) {
			EventBus.getDefault().post(new twoclientEVENT(message));
		}
		if(((Message) msg).getMessage().equals("newpassadmin")) {
			EventBus.getDefault().post(new newpassadmin_EVENT(message));
		}
		if(((Message) msg).getMessage().equals("showpricefun")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}
		if(((Message) msg).getMessage().equals("showpricefun2")) {
			EventBus.getDefault().post(new loginadminEvent2(message));
		}
		if(((Message) msg).getMessage().equals("yes")) {
			EventBus.getDefault().post(new new_subscirber_EVENT(message));
		}
		if(((Message) msg).getMessage().equals("writeareview")) {
			EventBus.getDefault().post(new reviewEvent(message));
		}
		if(((Message) msg).getMessage().equals("review_0")) {
			EventBus.getDefault().post(new reviewEvent(message));
		}
		if(((Message) msg).getMessage().equals("review_-1")) {
			EventBus.getDefault().post(new home_review_Event(message));
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
			System.out.println("hereeeee");
			EventBus.getDefault().post(new ResponseComplaintEvent(message));
		}
		if(message.getMessage().equals("2allComplaints")) {
			System.out.println("refresh in simple client");
			EventBus.getDefault().post(new RefreshComplaintsEvent(message));
		}

	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 6666);
		}
		return client;
	}

}
