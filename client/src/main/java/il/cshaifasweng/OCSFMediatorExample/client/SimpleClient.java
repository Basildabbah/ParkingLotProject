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

		//		*************************************************************************************
//		*************************************************************************************

		if(((Message) msg).getMessage().equals("CheckOrderStatus")) {
			EventBus.getDefault().post(new CheckOrderStatusEvent(message));
		}
		if(((Message) msg).getMessage().equals("CancelOrder")) {
			EventBus.getDefault().post(new CancelOrderEvent(message));
		}
		if(((Message) msg).getMessage().equals("GuestPreOrder")) {
			EventBus.getDefault().post(new GuestPreOrderEvent(message));
		}
		if(((Message) msg).getMessage().equals("GuestOnSiteOrder")) {
			EventBus.getDefault().post(new GuestOnSiteOrderEvent(message));
		}
		if(((Message) msg).getMessage().equals("RegularSubscriberOrder")) {
			EventBus.getDefault().post(new RegularSubscriberOrderEvent(message));
		}
		if(((Message) msg).getMessage().equals("FullSubscriberOrder")) {
			EventBus.getDefault().post(new FullSubscriberOrderEvent(message));
		}
		if(((Message) msg).getMessage().equals("RegularSubscriberOrder_enter")) {
			EventBus.getDefault().post(new RegularSubscriberOrder_enterEvent(message));
		}
		if(((Message) msg).getMessage().equals("FullSubscriberOrder_enter")) {
			EventBus.getDefault().post(new FullSubscriberOrder_enterEvent(message));
		}
		if(((Message) msg).getMessage().equals("CancelReservation")) {
			EventBus.getDefault().post(new CancelReserveEvent(message));
		}
		if(((Message) msg).getMessage().equals("Reservation")) {
			EventBus.getDefault().post(new ReserveParkingEvent(message));
		}

		if (message.getMessage().equals("InvalidSpotsReport")) {
			EventBus.getDefault().post(new InvalidSpotsReportEvent(message));
		}
		if (message.getMessage().equals("ComplaintsReport")) {
			EventBus.getDefault().post(new ComplaintsReportEvent(message));
		}
		if (message.getMessage().equals("OrdersReport")) {
			EventBus.getDefault().post(new OrdersReportEvent(message));
		}
		if (message.getMessage().equals("Stats")) {
			EventBus.getDefault().post(new StatsEvent(message));
		}



//		*************************************************************************************
//		*************************************************************************************

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
		/*if(((Message) msg).getMessage().equals("#caralreadylinked")) {
			EventBus.getDefault().post(new loginadminEvent(message));
		}*/
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
			EventBus.getDefault().post(new MessageWaErEvent((Message) (new Message("This car is already linked to another account"))));
		}
		if(msgString.equals("#caradded")){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("The car has been added to your account successfully"))));
		}
		if ((msgString.equals("#cardoesntexist"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaErEvent((Message) (new Message("There is no such car that is linked to your account"))));
		}
		if ((msgString.equals("#carremoved"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("The car has been removed from your account successfully"))));
		}
		if ((msgString.equals("#showparkinglotpicture"))){
			System.out.println("/da/da/d/a/d/a/d/a");
			Message msg1 = ((Message) msg);
			EventBus.getDefault().post(new CurrentPictureEvent((int[][][]) msg1.getObject1() , (int)msg1.getObject2()));
		}
		if ((msgString.equals("#CarEntered"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("Your car has been entered to the parking lot"))));
		}
		if ((msgString.equals("#TheCarIsAlreadyIn"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("Your car is already in the parking lot"))));
		}
		if ((msgString.equals("#SendToAlternative"))){
			System.out.println("stillnotready....");
		}
		if ((msgString.equals("#CarIsNotInParkingLot"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaErEvent((Message) (new Message("This car is not in the selected parking lot"))));
		}
		if ((msgString.equals("#CarExited"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("Your car has been exited from the parking lot"))));
		}
		if ((msgString.equals("#NumberOfColIsWrong"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaErEvent((Message) (new Message("Please enter a valid col number for the parking lot you have picked"))));
		}
		if ((msgString.equals("#SomeoneIsParked"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaErEvent((Message) (new Message("Someone is parked at the spot u have chose to deactivate so the process can not be completed"))));
		}
		if ((msgString.equals("#InactiveSuccess"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("The spot has been deactivated"))));
		}
		if ((msgString.equals("#ActiveSuccess"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("The spot has been activated"))));
		}
		if ((msgString.equals("#subscriptionrenewed"))){
			System.out.println("sclientada");
			EventBus.getDefault().post(new MessageWaInfEvent((Message) (new Message("Your subscription has been renewed"))));
		}


	}
	public static SimpleClient getClient() {
		if (client == null) {
			client = new SimpleClient("localhost", 7777);
		}
		return client;
	}

}