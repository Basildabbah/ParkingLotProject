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
		String msgString = msg.toString();
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
		if(msgString.equals("#caralreadylinked")) {
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("This car is already linked to another account"))));
		}
		if(msgString.equals("#caradded")){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("The car has been added to your account successfully"))));
		}
		if ((msgString.equals("#cardoesntexist"))){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("There is no such car that is linked to your account"))));
		}
		if ((msgString.equals("#carremoved"))){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("The car has been removed from your account successfully"))));
		}
		if ((msgString.equals("#showparkinglotpicture"))){
			Message msg1 = ((Message) msg);
			EventBus.getDefault().post(new CurrentPictureEvent((int[][][]) msg1.getObject1() , (int)msg1.getObject2()));
		}
		if ((msgString.equals("#carentered"))){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("Your car has been entered to the parking lot"))));
		}
		if ((msgString.equals("#SendToAlternative"))){

		}
		if ((msgString.equals("#CarIsNotInParkingLot"))){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("This car is not in the selected parking lot"))));
		}
		if ((msgString.equals("#CarExited"))){
			EventBus.getDefault().post(new MessageWaEvent((Message) (new Message("Your car has been exited from the parking lot"))));
		}
	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 6666);
		}
		return client;
	}

}
