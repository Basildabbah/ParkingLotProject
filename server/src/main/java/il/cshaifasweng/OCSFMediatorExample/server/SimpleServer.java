package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LocalDateType;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SimpleServer extends AbstractServer {
	private static Session session;
	private static SessionFactory sessionFactory = getSessionFactory();

	public SimpleServer(int port) {
		super(port);

	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(Admin.class);
		configuration.addAnnotatedClass(CanceledOrder.class);
		configuration.addAnnotatedClass(totalordertest.class);
		configuration.addAnnotatedClass(Car.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(OneTimeCustomer.class);
		configuration.addAnnotatedClass(OnSiteCustomer.class);
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(inactivenumber.class);
		configuration.addAnnotatedClass(ParkingLot.class);
		configuration.addAnnotatedClass(RegularSubscriber.class);
		configuration.addAnnotatedClass(ParkingLotManager.class);
		configuration.addAnnotatedClass(ParkingLotEmployee.class);
		configuration.addAnnotatedClass(FullSubscriber.class);
		configuration.addAnnotatedClass(Prices.class);
		configuration.addAnnotatedClass(ChainManager.class);
		configuration.addAnnotatedClass(review.class);
		configuration.addAnnotatedClass(totalordertest.class);

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void initializeData() {

	}

	public static <T> List<T> getAll(Class<T> object) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(object);
		Root<T> rootEntry = criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery = criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery = session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	private static String encrypt(String originalString, String secretKey) throws Exception {
		Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedBytes = cipher.doFinal(originalString.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	private static String decrypt(String encryptedString, String secretKey) throws Exception {
		Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedString));
		return new String(decryptedBytes);
	}

	//		*************************************************************************************
//		*************************************************************************************

	public static int HoursBetweenDates(int hour1, int day1, int month1, int year1, int hour2, int day2, int month2, int year2) {

		int difyears = year2 - year1;

		int difmonths=0, difdays=0, difhours=0;

		if (month2 >= month1) {
			difmonths = month2 - month1 + 12 * difyears;
		}

		if (month1 > month2) {
			difmonths = 12 - month1 + month2 + 12 * (difyears - 1);
		}

		if (day2 >= day1) {
			difdays = day2 - day1 + 28 * difmonths;
		}

		if (day1 > day2) {
			difdays = 28 - day1 + day2 + 28 * (difmonths - 1);
		}

		if (hour2 >= hour1) {
			difhours = hour2 - hour1 + 24 * difdays;
		}

		if (hour1 > hour2) {
			difhours = 24 - hour1 + hour2 + 24 * (difdays - 1);
		}

		return difhours;
	}

//		*************************************************************************************
//		*************************************************************************************


	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws Exception {
		String msgString = msg.toString();

//		*************************************************************************************
//		1
//		*************************************************************************************

		if (msgString.equals("#CheckOrderStatus")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("CheckOrderStatus");
			Message cpymsg = ((Message) msg);

			List<Order> ListOrders = getAll(Order.class);

			String PersonIdString = cpymsg.getObject1().toString();
			int PersonId=Integer.parseInt(PersonIdString);

			String Password = cpymsg.getObject2().toString();

			String NumberOfOrderString = cpymsg.getObject3().toString();
			int NumberOfOrder=Integer.parseInt(NumberOfOrderString);

			int CheckOrderStatus=0;

			int OrderID,PersonID,ParkingLotID,TotalHours,Payment,EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear;

			//Authenicate
			for(Order order : ListOrders)
			{
				if(order.getOrderId()==NumberOfOrder)
				{
					if(order.getPersonId()==PersonId && order.getPassword().equals(Password))
						CheckOrderStatus=1;
				}
			}

			if(CheckOrderStatus==0)
				message.setObject1("One Of The Details is Not Right");

			if(CheckOrderStatus==1)
			{
				message.setObject1("Check Order Status Accepted");
				for(Order order : ListOrders)
				{
					if (order.getOrderId()==NumberOfOrder)
					{
						TotalHours=HoursBetweenDates(order.getEnterHour(),order.getEnterDay(),order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear());

						if(order.getTypeOfOrder().equals("GuestPreOrder"))
						{
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(1); // guest pre order
							String arr[] = price12.split(" ");
							int price1 = Integer.parseInt(arr[0]);

							//***************************************
							Payment=TotalHours*price1;
							message.setObject2("The Payment in Money is: ");
							message.setObject3(Payment);
						}

						if(order.getTypeOfOrder().equals("GuestOnSiteOrder"))
						{
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(0); // guest on site
							String arr[] = price12.split(" ");
							int price2 = Integer.parseInt(arr[0]);

							//***************************************

							Payment=TotalHours*price2;
							message.setObject2("The Payment in Money is: ");
							message.setObject3(Payment);
						}
						if(order.getTypeOfOrder().equals("RegularSubscriberOrder"))
						{
							Payment=TotalHours;
							message.setObject2("The Payment in Hours is: ");
							message.setObject3(Payment);
						}
						if(order.getTypeOfOrder().equals("FullSubscriberOrder"))
						{
							Payment=TotalHours;
							message.setObject2("The Payment in Hours is: ");
							message.setObject3(Payment);
						}

						OrderID=order.getOrderId();
						message.setObject4(OrderID);

						PersonID=order.getPersonId();
						message.setObject5(PersonID);

						ParkingLotID=order.getParkingLotId();
						message.setObject6(ParkingLotID);

						EnterHour=order.getEnterHour();
						message.setObject7(EnterHour);

						EnterDay=order.getEnterDay();
						message.setObject8(EnterDay);

						EnterMonth=order.getEnterMonth();
						message.setObject9(EnterMonth);

						EnterYear=order.getEnterYear();
						message.setObject10(EnterYear);

						ExitHour=order.getExitHour();
						message.setObject11(ExitHour);

						ExitDay=order.getExitDay();
						message.setObject12(ExitDay);

						ExitMonth=order.getExitMonth();
						message.setObject13(ExitMonth);

						ExitYear=order.getExitYear();
						message.setObject14(ExitYear);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

//		*************************************************************************************
//		2
//		*************************************************************************************

		if (msgString.equals("#CancelOrder")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("CancelOrder");
			Message cpymsg = ((Message) msg);

			List<Order> ListOrders = getAll(Order.class);
			List<RegularSubscriber> ListRegularSubscriber = getAll(RegularSubscriber.class);
			List<FullSubscriber> ListFullSubscriber = getAll(FullSubscriber.class);
			List<ParkingLot> ListParkingLot = getAll(ParkingLot.class);
			List<Car> ListCar = getAll(Car.class);
			List<CanceledOrder> tt = getAll(CanceledOrder.class);

			String PersonIdString = cpymsg.getObject1().toString();
			int PersonId=Integer.parseInt(PersonIdString);

			String Password = cpymsg.getObject2().toString();

			String NumberOfOrderString = cpymsg.getObject3().toString();
			int NumberOfOrder=Integer.parseInt(NumberOfOrderString);

			int FlagCancelOrder=0;

			int TotalHours=0;

			double Refund=0;

			int CarNumber=0;

			//Authenicate
			for(Order order : ListOrders)
			{
				if(order.getOrderId()==NumberOfOrder)
				{
					if(order.getPersonId()==PersonId && order.getPassword().equals(Password))
						FlagCancelOrder=1;
				}
			}

			if(FlagCancelOrder==0)
				message.setObject1("One Of The Details is Not Right");

			if(FlagCancelOrder==1)
			{
				message.setObject1("Your Order Has Been Canceled");
				for(Order order : ListOrders)
				{
					if (order.getOrderId()==NumberOfOrder)
					{
						for(Car car : ListCar)
						{
							if (car.getCarNumber()==order.getCarNumber()){}
							//	session.delete(car);
						}

						TotalHours=HoursBetweenDates(order.getEnterHour(),order.getEnterDay(),order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear());

						if(order.getTypeOfOrder().equals("GuestPreOrder"))
						{
							System.out.println("canceorderguestpreorder");
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(1); // guest pre order
							String arr[] = price12.split(" ");
							int price2 = Integer.parseInt(arr[0]);

							//***************************************

							Refund=TotalHours*price2;
							LocalDateTime now=LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDateTime dateTime = LocalDateTime.parse(order.getEntryTime(), formatter);
							long diffInHours = ChronoUnit.HOURS.between(now, dateTime);
							if ( diffInHours >3) {
								Refund *=0.9;
							}
							else if ( diffInHours>1 ) {
								Refund *=0.5;
							}
							else if ( diffInHours>0 ) {
								Refund *=0.1;
							}
							else {
								Refund =0;
							}
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("GuestOnSiteOrder"))
						{
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(0); // guest on site
							String arr[] = price12.split(" ");
							int price1 = Integer.parseInt(arr[0]);

							//***************************************
							Refund = TotalHours * price1;
							LocalDateTime now=LocalDateTime.now();
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
							LocalDateTime dateTime = LocalDateTime.parse(order.getEntryTime(), formatter);
							long diffInHours = ChronoUnit.HOURS.between(now, dateTime);
							if ( diffInHours >3) {
								Refund *=0.9;
							}
							else if ( diffInHours>1 ) {
								Refund *=0.5;
							}
							else if ( diffInHours>0 ) {
								Refund *=0.1;
							}
							else {
								Refund =0;
							}
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("RegularSubscriberOrder"))
						{
							for(RegularSubscriber Subscriber : ListRegularSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}

						if(order.getTypeOfOrder().equals("FullSubscriberOrder"))
						{
							for(FullSubscriber Subscriber : ListFullSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}
						for (ParkingLot parkingLot:ListParkingLot)
						{
							if(parkingLot.getId()==order.getParkingLotId())
							{
								parkingLot.setNumberOfOrders(parkingLot.getNumberOfOrders()-1);
								session.update(parkingLot);
							}
						}
						/*int OrderId,String TypeOfOrder,int EnterHour, int EnterDay, int EnterMonth, int EnterYear
						 , int ExitHour, int ExitDay
						, int ExitMonth, int ExitYear,int ParkingLotId, int PersonId, String Password*/
						CanceledOrder t=new CanceledOrder(order.getOrderId(),order.getTypeOfOrder(),order.getEnterHour(),order.getEnterDay()
								,order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear()
								,order.getParkingLotId(),order.getPersonId(),order.getPassword());
						session.save(t);
						session.delete(order);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (msgString.equals("#CancelReservation")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("CancelReservation");
			Message cpymsg = ((Message) msg);

			List<Order> ListOrders = getAll(Order.class);
			List<RegularSubscriber> ListRegularSubscriber = getAll(RegularSubscriber.class);
			List<FullSubscriber> ListFullSubscriber = getAll(FullSubscriber.class);
			List<ParkingLot> ListParkingLot = getAll(ParkingLot.class);
			List<Car> ListCar = getAll(Car.class);
			List<CanceledOrder> tt = getAll(CanceledOrder.class);

			String PersonIdString = cpymsg.getObject1().toString();
			int PersonId=Integer.parseInt(PersonIdString);

			String Password = cpymsg.getObject2().toString();

			String NumberOfOrderString = cpymsg.getObject3().toString();
			int NumberOfOrder=Integer.parseInt(NumberOfOrderString);

			int FlagCancelOrder=0;

			int TotalHours=0;

			int Refund=0;

			int CarNumber=0;

			//Authenicate
			for(Order order : ListOrders)
			{
				if(order.getOrderId()==NumberOfOrder)
				{
					if(order.getPersonId()==PersonId && order.getPassword().equals(Password))
						FlagCancelOrder=1;
				}
			}

			if(FlagCancelOrder==0)
				message.setObject1("One Of The Details is Not Right");

			if(FlagCancelOrder==1)
			{
				message.setObject1("Your Order Has Been Canceled");
				for(Order order : ListOrders)
				{
					if (order.getOrderId()==NumberOfOrder)
					{
						for(Car car : ListCar)
						{
							if (car.getCarNumber()==order.getCarNumber()){}
								//session.delete(car);
						}

						TotalHours=HoursBetweenDates(order.getEnterHour(),order.getEnterDay(),order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear());

						if(order.getTypeOfOrder().equals("GuestPreOrder"))
						{
							System.out.println("canceorderguestpreorder");
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(1); // guest pre order
							String arr[] = price12.split(" ");
							int price2 = Integer.parseInt(arr[0]);

							//***************************************

							Refund=TotalHours*price2;
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("GuestOnSiteOrder"))
						{
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(0); // guest on site
							String arr[] = price12.split(" ");
							int price1 = Integer.parseInt(arr[0]);

							//***************************************

							Refund=TotalHours*price1;
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("RegularSubscriberOrder"))
						{
							for(RegularSubscriber Subscriber : ListRegularSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}

						if(order.getTypeOfOrder().equals("FullSubscriberOrder"))
						{
							for(FullSubscriber Subscriber : ListFullSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}
						for (ParkingLot parkingLot:ListParkingLot)
						{
							if(parkingLot.getId()==order.getParkingLotId())
							{
								parkingLot.setNumberOfOrders(parkingLot.getNumberOfOrders()-1);
								session.update(parkingLot);
							}
						}
						/*int OrderId,String TypeOfOrder,int EnterHour, int EnterDay, int EnterMonth, int EnterYear
						 , int ExitHour, int ExitDay
						, int ExitMonth, int ExitYear,int ParkingLotId, int PersonId, String Password*/
						CanceledOrder t=new CanceledOrder(order.getOrderId(),order.getTypeOfOrder(),order.getEnterHour(),order.getEnterDay()
								,order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear()
								,order.getParkingLotId(),order.getPersonId(),order.getPassword());
						session.save(t);
						session.delete(order);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

//		*************************************************************************************
//		3
//		*************************************************************************************

		if (msgString.equals("#GuestPreOrder")){

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("GuestPreOrder");
			Message cpymsg = ((Message) msg);

//***************************************

			int i = Integer.parseInt(cpymsg.getObject9().toString()) - 1; // parking lot id object 1
			List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

			List<String> Prices = PricesList.get(i).getValueNote();
			String price12 = Prices.get(1); // guest pre order
			String arr[] = price12.split(" ");
			int price2 = Integer.parseInt(arr[0]);

//***************************************

			String TypeOfOrder = "GuestPreOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour=Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay=Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth=Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear=Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour=Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay=Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth=Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear=Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId=Integer.parseInt(ParkingLotIdString);

			String IDString = cpymsg.getObject10().toString();
			int ID=Integer.parseInt(IDString);

			String Password = cpymsg.getObject11().toString();

			boolean OnSite = (boolean)cpymsg.getObject12();

			String CarNumberString = cpymsg.getObject13().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject14().toString();

			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int TotalHours = HoursBetweenDates(EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear);

			int NumberOfOrdersInTheSameHours=0;
			int MaxOrderId=0;
			int Payment=0;
			int FlagOrder=1;

			ParkingLot park1 = null;

			for (ParkingLot ParkingLot : ListParkingLots)
			{
				//Check which ParkingLot in ListParkingLot has the same Id
				if(ParkingLot.getId()==ParkingLotId)
				{
					park1 = ParkingLot;
					for(Order Order: ListOrders)
					{
						//go through all orders in same Parking Lot
						if(Order.getParkingLotId() == ParkingLotId )
						{
							//check how many orders between the two dates
							if(Order.getEnterYear() <= ExitYear && Order.getExitYear()>=EnterYear)
							{
								if(Order.getEnterMonth() <= ExitMonth && Order.getExitMonth()>=EnterMonth)
								{
									if(Order.getEnterDay() <= ExitDay && Order.getExitDay()>=EnterDay)
									{
										if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
											if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
												//sum all orders in same parking lot in same hours
												NumberOfOrdersInTheSameHours++;
											}
										}
									}
								}
							}
						}
					}

					//check if there is empty space
					if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0)
					{
						Payment = TotalHours * price2;

						for(Order Order: ListOrders)
						{
							//go through all orders in same Parking Lot
							if(Order.getOrderId() > MaxOrderId )
							{
								MaxOrderId = Order.getOrderId();
							}
						}
						MaxOrderId++;
						FlagOrder=1;
					}
					else
					{
						FlagOrder=0;
					}
				}
			}

			if (FlagOrder==0)
			{
				message.setObject1("The Parking Lot is Full, Please Choose Another Parking Lot");
				System.out.println("The Parking Lot is Full, Please Choose Another Parking Lot");
			}

			if(FlagOrder==1)
			{
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setGuest(1+totalordertest2.getGuest());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId,TypeOfOrder,EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear,ParkingLotId,ID,Password,EnterMin,ExitMin);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(-1);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

				/*Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/

				//NewOrder.setParkinglot(park1);

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(Payment);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try
			{
				client.sendToClient(message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (msgString.equals("#Reservation")){

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("Reservation");
			Message cpymsg = ((Message) msg);

//***************************************

			int i = Integer.parseInt(cpymsg.getObject9().toString()) - 1; // parking lot id object 1
			List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

			List<String> Prices = PricesList.get(i).getValueNote();
			String price12 = Prices.get(1); // guest pre order
			String arr[] = price12.split(" ");
			int price2 = Integer.parseInt(arr[0]);

//***************************************

			String TypeOfOrder = "GuestPreOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour=Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay=Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth=Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear=Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour=Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay=Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth=Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear=Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId=Integer.parseInt(ParkingLotIdString);

			String IDString = cpymsg.getObject10().toString();
			int ID=Integer.parseInt(IDString);

			String Password = cpymsg.getObject11().toString();

			boolean OnSite = (boolean)cpymsg.getObject12();

			String CarNumberString = cpymsg.getObject13().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject14().toString();


			int TotalHours = HoursBetweenDates(EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear);

			int NumberOfOrdersInTheSameHours=0;
			int MaxOrderId=0;
			int Payment=0;
			int FlagOrder=1;

			ParkingLot park1 = null;

			for (ParkingLot ParkingLot : ListParkingLots)
			{
				//Check which ParkingLot in ListParkingLot has the same Id
				if(ParkingLot.getId()==ParkingLotId)
				{
					park1 = ParkingLot;
					for(Order Order: ListOrders)
					{
						//go through all orders in same Parking Lot
						if(Order.getParkingLotId() == ParkingLotId )
						{
							//check how many orders between the two dates
							if(Order.getEnterYear() <= ExitYear && Order.getExitYear()>=EnterYear)
							{
								if(Order.getEnterMonth() <= ExitMonth && Order.getExitMonth()>=EnterMonth)
								{
									if(Order.getEnterDay() <= ExitDay && Order.getExitDay()>=EnterDay)
									{
										if(Order.getEnterHour() <= ExitHour && Order.getExitHour()>=EnterHour)
										{
											//sum all orders in same parking lot in same hours
											NumberOfOrdersInTheSameHours++;
										}
									}
								}
							}
						}
					}

					//check if there is empty space
					if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0)
					{
						Payment = TotalHours * price2;

						for(Order Order: ListOrders)
						{
							//go through all orders in same Parking Lot
							if(Order.getOrderId() > MaxOrderId )
							{
								MaxOrderId = Order.getOrderId();
							}
						}
						MaxOrderId++;
						FlagOrder=1;
					}
					else
					{
						FlagOrder=0;
					}
				}
			}

			if (FlagOrder==0)
			{
				message.setObject1("The Parking Lot is Full, Please Choose Another Parking Lot");
				System.out.println("The Parking Lot is Full, Please Choose Another Parking Lot");
			}

			if(FlagOrder==1)
			{
				Order NewOrder = new Order(MaxOrderId,TypeOfOrder,EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear,ParkingLotId,ID,Password);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, 0);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(-1);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

				/*Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/

				//NewOrder.setParkinglot(park1);

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(Payment);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try
			{
				client.sendToClient(message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
//		*************************************************************************************
//		4
//		*************************************************************************************

		if (msgString.equals("#GuestOnSiteOrder"))
		{

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("GuestOnSiteOrder");
			Message cpymsg = ((Message) msg);


//***************************************

			int i = Integer.parseInt(cpymsg.getObject9().toString()) - 1; // parking lot id object 1
			List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

			List<String> Prices = PricesList.get(i).getValueNote();
			String price12 = Prices.get(0);
			String arr[] = price12.split(" ");
			int price1 = Integer.parseInt(arr[0]);

//***************************************

			String TypeOfOrder = "GuestOnSiteOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour=Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay=Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth=Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear=Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour=Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay=Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth=Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear=Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId=Integer.parseInt(ParkingLotIdString);

			String IDString = cpymsg.getObject10().toString();
			int ID=Integer.parseInt(IDString);

			String Password = cpymsg.getObject11().toString();

			boolean OnSite = (boolean)cpymsg.getObject12();

			String CarNumberString = cpymsg.getObject13().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject14().toString();
			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int TotalHours = HoursBetweenDates(EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear);

			int NumberOfOrdersInTheSameHours=0;
			int MaxOrderId=0;
			int Payment=0;
			int FlagOrder=0;

			ParkingLot park1 = null;

			for (ParkingLot ParkingLot : ListParkingLots)
			{
				//Check which ParkingLot in ListParkingLot has the same Id
				if(ParkingLot.getId()==ParkingLotId)
				{
					park1 = ParkingLot;
					for(Order Order: ListOrders)
					{
						//go through all orders in same Parking Lot
						if(Order.getParkingLotId() == ParkingLotId )
						{
							//check how many orders between the two dates
							if(Order.getEnterYear() <= ExitYear && Order.getExitYear()>=EnterYear)
							{
								if(Order.getEnterMonth() <= ExitMonth && Order.getExitMonth()>=EnterMonth)
								{
									if(Order.getEnterDay() <= ExitDay && Order.getExitDay()>=EnterDay)
									{
										if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
											if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
												//sum all orders in same parking lot in same hours
												NumberOfOrdersInTheSameHours++;
											}
										}
									}
								}
							}
						}
					}
					int numberAlreadyParkingLot=0;
					for (Order order1:ListOrders)
					{
						if(order1.getParkingLotId()==ParkingLotId)
						{
							if (order1.isAlreadyInParkingLot())
							{
								numberAlreadyParkingLot+=1;
							}
						}
					}
					//check if there is empty space
					if (ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0 &&ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot>0 )
					{

						for(Order Order: ListOrders)
						{
							//go through all orders in same Parking Lot
							if(Order.getOrderId() > MaxOrderId )
							{
								MaxOrderId = Order.getOrderId();
							}
						}
						FlagOrder=1;
						Payment = TotalHours * price1;
						MaxOrderId++;
					}
					else
					{
						FlagOrder=0;
					}
				}
			}

			if (FlagOrder==0)
			{
				message.setObject1("The Parking Lot is Full, Please Choose Another Parking Lot");
			}

			if(FlagOrder==1)
			{
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setGuest(1+totalordertest2.getGuest());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId,TypeOfOrder,EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear,ParkingLotId,ID,Password,EnterMin,ExitMin);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(-1);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

			/*	Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/


				//NewOrder.setParkinglot(park1);
			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(Payment);

				for (ParkingLot ParkingLot : ListParkingLots)
				{
					//Check which ParkingLot in ListParkingLot has the same Id
					if (ParkingLot.getId() == ParkingLotId) {
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try
			{
				client.sendToClient(message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		if (msgString.equals("#GuestOnSiteOrder_enter"))
		{

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("GuestOnSiteOrder_enter");
			Message cpymsg = ((Message) msg);
			System.out.println("Hour "+cpymsg.getObject1());
			System.out.println("Day "+cpymsg.getObject2());
			System.out.println("month "+cpymsg.getObject3());
			System.out.println("year "+cpymsg.getObject4());
			System.out.println("exit Hour "+cpymsg.getObject5());
			System.out.println("exit day "+cpymsg.getObject6());
			System.out.println("exit month "+cpymsg.getObject7());
			System.out.println("exit year "+cpymsg.getObject8());
			System.out.println("ParkLotIdMenu   "+cpymsg.getObject9());
			System.out.println("ID   "+cpymsg.getObject10());
			System.out.println("Password   "+cpymsg.getObject11());
			System.out.println("ID   "+cpymsg.getObject12());
			System.out.println("OnSite   "+cpymsg.getObject13());
			System.out.println("CarNumber   "+cpymsg.getObject14());
			System.out.println("email   "+cpymsg.getObject15());
//***************************************

			int i = Integer.parseInt(cpymsg.getObject9().toString()) - 1; // parking lot id object 1
			List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

			List<String> Prices = PricesList.get(i).getValueNote();
			String price12 = Prices.get(0);
			String arr[] = price12.split(" ");
			int price1 = Integer.parseInt(arr[0]);

//***************************************
			String TypeOfOrder = "GuestOnSiteOrder";
			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<Order> ListOrders = getAll(Order.class);
			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour=Integer.parseInt(EnterHourString);
			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay=Integer.parseInt(EnterDayString);
			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth=Integer.parseInt(EnterMonthString);
			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear=Integer.parseInt(EnterYearString);
			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour=Integer.parseInt(ExitHourString);
			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay=Integer.parseInt(ExitDayString);
			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth=Integer.parseInt(ExitMonthString);
			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear=Integer.parseInt(ExitYearString);
			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId=Integer.parseInt(ParkingLotIdString);
			String IDString = cpymsg.getObject10().toString();
			int ID=Integer.parseInt(IDString);
			String Password = cpymsg.getObject11().toString();
			//boolean OnSite = (boolean)cpymsg.getObject12();
			System.out.println("13");
			String CarNumberString = cpymsg.getObject14().toString();
			System.out.println("13.5");
			System.out.println(CarNumberString);
			int CarNumber = Integer.parseInt(CarNumberString);
			System.out.println("14");
			String Email = cpymsg.getObject15().toString();
			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int TotalHours = HoursBetweenDates(EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear);
			System.out.println("15");
			int NumberOfOrdersInTheSameHours=0;
			int MaxOrderId=0;
			int Payment=0;
			int FlagOrder=0;
			System.out.println("16");
			ParkingLot park1 = null;
			for (ParkingLot ParkingLot : ListParkingLots)
			{
				//Check which ParkingLot in ListParkingLot has the same Id
				if(ParkingLot.getId()==ParkingLotId)
				{
					park1 = ParkingLot;
					for(Order Order: ListOrders)
					{
						//go through all orders in same Parking Lot
						if(Order.getParkingLotId() == ParkingLotId )
						{
							//check how many orders between the two dates
							if(Order.getEnterYear() <= ExitYear && Order.getExitYear()>=EnterYear)
							{
								if(Order.getEnterMonth() <= ExitMonth && Order.getExitMonth()>=EnterMonth)
								{
									if(Order.getEnterDay() <= ExitDay && Order.getExitDay()>=EnterDay)
									{
										if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
											if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
												//sum all orders in same parking lot in same hours
												NumberOfOrdersInTheSameHours++;
											}
										}
									}
								}
							}
						}
					}
					int numberAlreadyParkingLot=0;
					for (Order order1:ListOrders)
					{
						if(order1.getParkingLotId()==ParkingLotId)
						{
							if (order1.isAlreadyInParkingLot())
							{
								numberAlreadyParkingLot+=1;
							}
						}
					}
					//check if there is empty space
					if (ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0 &&ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot>0 )
					{

						for(Order Order: ListOrders)
						{
							//go through all orders in same Parking Lot
							if(Order.getOrderId() > MaxOrderId )
							{
								MaxOrderId = Order.getOrderId();
							}
						}
						FlagOrder=1;
						Payment = TotalHours * price1;
						MaxOrderId++;
					}
					else
					{
						FlagOrder=0;
					}
				}
			}

			if (FlagOrder==0)
			{
				message.setObject1("The Parking Lot is Full, Please Choose Another Parking Lot");
			}

			if(FlagOrder==1)
			{
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setGuest(1+totalordertest2.getGuest());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId,TypeOfOrder,EnterHour,EnterDay,EnterMonth,EnterYear,ExitHour,ExitDay,ExitMonth,ExitYear,ParkingLotId,ID,Password,EnterMin,ExitMin);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(-1);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

			/*	Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/


				//NewOrder.setParkinglot(park1);
			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(Payment);

				for (ParkingLot ParkingLot : ListParkingLots)
				{
					//Check which ParkingLot in ListParkingLot has the same Id
					if (ParkingLot.getId() == ParkingLotId) {
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try
			{
				client.sendToClient(message);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
//		*************************************************************************************
//		5
//		*************************************************************************************
		if (msgString.equals("#updatematrix")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {
					int rows = parkingLot.getNumberOfRows();
					int columns = parkingLot.getNumberOfColumns();
					int depth = parkingLot.getDepth();
					//System.out.println(columns);
					//System.out.println(depth);
					byte[] matrix1d = parkingLot.getMatrix();
					int[][][] matrix3d = new int[rows][columns][depth];
					int index = 0;  // Index into 1D array
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < columns; j++) {
							for (int k = 0; k < depth; k++) {
								matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
								index++;
							}
						}
					}
					//System.out.println("no1");
					try {
						//System.out.println("yes1");
						client.sendToClient(new Message("#updatematrix", matrix3d, columns));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#RegularSubscriberOrder")){

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("RegularSubscriberOrder");
			Message cpymsg = ((Message) msg);

			String TypeOfOrder = "RegularSubscriberOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<RegularSubscriber> ListRegularSubscriber = getAll(RegularSubscriber.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour = Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay = Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth = Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear = Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour = Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay = Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth = Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear = Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId = Integer.parseInt(ParkingLotIdString);

			String PersonIDString = cpymsg.getObject10().toString();
			int PersonID = Integer.parseInt(PersonIDString);

			String Password = cpymsg.getObject11().toString();

			String SubscriptionIdString = cpymsg.getObject12().toString();
			int SubscriptionId = Integer.parseInt(SubscriptionIdString);

			boolean OnSite = (boolean)cpymsg.getObject13();

			String CarNumberString = cpymsg.getObject14().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject15().toString();


			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int TotalHours = HoursBetweenDates(EnterHour ,EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear);

			int Payment=TotalHours;
			int NumberOfOrdersInTheSameHours = 0;
			int MaxOrderId = 0;
			int FlagDay = 1;
			int FlagRemainingHours = 0;
			int FlagSubscriber = 0;
			int FlagEmptySpot = 0;
			int FlagOrder = 0;

			ParkingLot park1 = null;

			for (RegularSubscriber Subscriber : ListRegularSubscriber) {
				if (Subscriber.getSubscriptionId() == SubscriptionId) {
					FlagSubscriber = 1;
					for (ParkingLot ParkingLot : ListParkingLots) {
						//Check which ParkingLot in ListParkingLots has the same Id
						if (ParkingLot.getId() == ParkingLotId) {
							park1 = ParkingLot;
							for (Order Order : ListOrders) {
								//go through all orders in same Parking Lot
								if (Order.getParkingLotId() == ParkingLotId) {
									//check how many orders between the two dates
									if (Order.getEnterYear() <= ExitYear && Order.getExitYear() >= EnterYear) {
										if (Order.getEnterMonth() <= ExitMonth && Order.getExitMonth() >= EnterMonth) {
											if (Order.getEnterDay() <= ExitDay && Order.getExitDay() >= EnterDay) {
												if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
													if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
														//sum all orders in same parking lot in same hours
														NumberOfOrdersInTheSameHours++;
													}
												}
											}
										}
									}
								}
							}

							//check if there's empty spots
							if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0) {
								FlagEmptySpot = 1;
								//check if subscriber have enough hours
								if (Subscriber.getRemainingHours() >= TotalHours) {
									FlagRemainingHours = 1;
									//go through all orders for this person and check if he got two orders in same day
									for (Order Order : ListOrders) {
										if (Order.getPersonId() == PersonID) {
											if (Order.getEnterDay() == EnterDay) {
												FlagDay = 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if (FlagDay == 1 && FlagRemainingHours == 1 && FlagEmptySpot == 1 && FlagSubscriber == 1)
				FlagOrder = 1;

			if (FlagOrder == 1) {
				for (Order Order : ListOrders) {
					if (Order.getOrderId() > MaxOrderId)
						MaxOrderId = Order.getOrderId();
				}
				MaxOrderId++;
				for (RegularSubscriber Subscriber : ListRegularSubscriber)
				{
					if (Subscriber.getId() == PersonID) {
						Subscriber.setRemainingHours(Subscriber.getRemainingHours() - TotalHours);
						session.update(Subscriber);
					}
				}

				Order NewOrder = new Order(MaxOrderId, TypeOfOrder, EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear, ParkingLotId, PersonID, Password,EnterMin,ExitMin);
				//NewOrder.setParkinglot(park1);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(PersonID);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

				Car NewCar = new Car(CarNumber);
				session.save(NewCar);

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setReg(1+totalordertest2.getReg());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(TotalHours);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			if (FlagSubscriber == 0)
				message.setObject1("The Subscription Number is Wrong");
			else
			if (FlagEmptySpot == 0)
				message.setObject1("The ParkingLot is Full");
			else
			if (FlagDay == 0)
				message.setObject1("You Only Allowed To Enter Once a Day");
			else
			if (FlagRemainingHours == 0)
				message.setObject1("You Don't Have Enough Hours");

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (msgString.equals("#RegularSubscriberOrder_enter")){

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("RegularSubscriberOrder_enter");
			Message cpymsg = ((Message) msg);

			String TypeOfOrder = "RegularSubscriberOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<RegularSubscriber> ListRegularSubscriber = getAll(RegularSubscriber.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour = Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay = Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth = Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear = Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour = Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay = Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth = Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear = Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId = Integer.parseInt(ParkingLotIdString);

			String PersonIDString = cpymsg.getObject10().toString();
			int PersonID = Integer.parseInt(PersonIDString);

			String Password = cpymsg.getObject11().toString();

			String SubscriptionIdString = cpymsg.getObject12().toString();
			int SubscriptionId = Integer.parseInt(SubscriptionIdString);

			boolean OnSite = (boolean)cpymsg.getObject13();

			String CarNumberString = cpymsg.getObject14().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject15().toString();
			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int TotalHours = HoursBetweenDates(EnterHour ,EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear);

			int Payment=TotalHours;
			int NumberOfOrdersInTheSameHours = 0;
			int MaxOrderId = 0;
			int FlagDay = 1;
			int FlagRemainingHours = 0;
			int FlagSubscriber = 0;
			int FlagEmptySpot = 0;
			int FlagOrder = 0;

			ParkingLot park1 = null;

			for (RegularSubscriber Subscriber : ListRegularSubscriber) {
				if (Subscriber.getSubscriptionId() == SubscriptionId) {
					FlagSubscriber = 1;
					for (ParkingLot ParkingLot : ListParkingLots) {
						//Check which ParkingLot in ListParkingLots has the same Id
						if (ParkingLot.getId() == ParkingLotId) {
							park1 = ParkingLot;
							for (Order Order : ListOrders) {
								//go through all orders in same Parking Lot
								if (Order.getParkingLotId() == ParkingLotId) {
									//check how many orders between the two dates
									if (Order.getEnterYear() <= ExitYear && Order.getExitYear() >= EnterYear) {
										if (Order.getEnterMonth() <= ExitMonth && Order.getExitMonth() >= EnterMonth) {
											if (Order.getEnterDay() <= ExitDay && Order.getExitDay() >= EnterDay) {
												if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
													if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
														//sum all orders in same parking lot in same hours
														NumberOfOrdersInTheSameHours++;
													}
												}
											}
										}
									}
								}
							}

							//check if there's empty spots
							if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0) {
								FlagEmptySpot = 1;
								//check if subscriber have enough hours
								if (Subscriber.getRemainingHours() >= TotalHours) {
									FlagRemainingHours = 1;
									//go through all orders for this person and check if he got two orders in same day
									for (Order Order : ListOrders) {
										if (Order.getPersonId() == PersonID) {
											if (Order.getEnterDay() == EnterDay) {
												FlagDay = 0;
											}
										}
									}
								}
							}
						}
					}
				}
			}

			if (FlagDay == 1 && FlagRemainingHours == 1 && FlagEmptySpot == 1 && FlagSubscriber == 1)
				FlagOrder = 1;

			if (FlagOrder == 1) {
				for (Order Order : ListOrders) {
					if (Order.getOrderId() > MaxOrderId)
						MaxOrderId = Order.getOrderId();
				}
				MaxOrderId++;
				for (RegularSubscriber Subscriber : ListRegularSubscriber)
				{
					if (Subscriber.getId() == PersonID) {
						Subscriber.setRemainingHours(Subscriber.getRemainingHours() - TotalHours);
						session.update(Subscriber);
					}
				}
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setReg(1+totalordertest2.getReg());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId, TypeOfOrder, EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear, ParkingLotId, PersonID, Password,EnterMin,ExitMin);
				//NewOrder.setParkinglot(park1);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(PersonID);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

			/*	Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(TotalHours);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			if (FlagSubscriber == 0)
				message.setObject1("The Subscription Number is Wrong");
			else
			if (FlagEmptySpot == 0)
				message.setObject1("The ParkingLot is Full");
			else
			if (FlagDay == 0)
				message.setObject1("You Only Allowed To Enter Once a Day");
			else
			if (FlagRemainingHours == 0)
				message.setObject1("You Don't Have Enough Hours");

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		*************************************************************************************
//		6
//		*************************************************************************************

		if (msgString.equals("#FullSubscriberOrder")){

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("FullSubscriberOrder");
			Message cpymsg = ((Message) msg);
			System.out.println(cpymsg.getObject14().toString());
			String TypeOfOrder = "FullSubscriberOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<FullSubscriber> ListFullSubscriber = getAll(FullSubscriber.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour = Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay = Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth = Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear = Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour = Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay = Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth = Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear = Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId = Integer.parseInt(ParkingLotIdString);

			String PersonIDString = cpymsg.getObject10().toString();
			int PersonID = Integer.parseInt(PersonIDString);

			String Password = cpymsg.getObject11().toString();

			String SubscriptionIdString = cpymsg.getObject12().toString();
			int SubscriptionId = Integer.parseInt(SubscriptionIdString);

			boolean OnSite = (boolean)cpymsg.getObject13();

			String CarNumberString = cpymsg.getObject14().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject15().toString();
			System.out.println(Email);
			int TotalHours = HoursBetweenDates(EnterHour ,EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear);
			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int Payment=TotalHours;
			int NumberOfOrdersInTheSameHours = 0;
			int MaxOrderId = 0;
			int FlagRemainingHours = 0;
			int FlagSubscriber = 0;
			int FlagEmptySpot = 0;
			int FlagOrder = 0;

			ParkingLot park1 = null;
			System.out.println("hohohoh");
			for (FullSubscriber Subscriber : ListFullSubscriber) {
				if (Subscriber.getSubscriptionId() == SubscriptionId) {
					FlagSubscriber = 1;
					for (ParkingLot ParkingLot : ListParkingLots) {
						//Check which ParkingLot in ListParkingLots has the same Id
						if (ParkingLot.getId() == ParkingLotId) {
							park1 = ParkingLot;
							for (Order Order : ListOrders) {
								//go through all orders in same Parking Lot
								if (Order.getParkingLotId() == ParkingLotId) {
									//check how many orders between the two dates

									if (Order.getEnterYear() <= ExitYear && Order.getExitYear() >= EnterYear) {
										if (Order.getEnterMonth() <= ExitMonth && Order.getExitMonth() >= EnterMonth) {
											if (Order.getEnterDay() <= ExitDay && Order.getExitDay() >= EnterDay) {
												if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
													if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
														//sum all orders in same parking lot in same hours
														NumberOfOrdersInTheSameHours++;
													}
												}
											}
										}
									}
								}
							}
/*		if(OnSite==true) {
								int numberAlreadyParkingLot = 0;
								for (Order order1 : ListOrders) {

									if (order1.getParkingLotId() == ParkingLotId) {
										if (order1.isAlreadyInParkingLot()) {
											numberAlreadyParkingLot += 1;
										}
									}
								}
								if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot > 0) {
									FlagEmptySpot = 1;
									//check if subscriber have enough hours
									if (Subscriber.getRemainingHours() >= TotalHours) {
										FlagRemainingHours = 1;
										//go through all orders for this person and check if he got two orders in same day
										for (Order Order : ListOrders) {
											if (Order.getPersonId() == PersonID) {
												if (Order.getEnterDay() == EnterDay) {
													FlagDay = 0;
												}
											}
										}
									}
								}
							}*/
							if(OnSite==true) {
								int numberAlreadyParkingLot = 0;
								for (Order order1 : ListOrders) {

									if (order1.getParkingLotId() == ParkingLotId) {
										if (order1.isAlreadyInParkingLot()) {
											numberAlreadyParkingLot += 1;
										}
									}
								}
								if (ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0
										&&ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot>0 ) {
									FlagEmptySpot = 1;
									//check if subscriber have enough hours
									if (Subscriber.getRemainingHours() >= TotalHours) {
										FlagRemainingHours = 1;
									}
								}
							}
							//check if there's empty spots
							else if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0) {
								FlagEmptySpot = 1;
								//check if subscriber have enough hours
								if (Subscriber.getRemainingHours() >= TotalHours) {
									FlagRemainingHours = 1;
								}
							}
						}
					}
				}
			}

			if (FlagRemainingHours == 1 && FlagEmptySpot == 1 && FlagSubscriber == 1)
				FlagOrder = 1;

			if (FlagOrder == 1) {
				for (Order Order : ListOrders) {
					if (Order.getOrderId() > MaxOrderId)
						MaxOrderId = Order.getOrderId();
				}
				MaxOrderId++;
				for (FullSubscriber Subscriber : ListFullSubscriber)
				{
					if (Subscriber.getId() == PersonID) {
						Subscriber.setRemainingHours(Subscriber.getRemainingHours() - TotalHours);
						session.update(Subscriber);
					}
				}
				/********************************************************************/
				List<totalordertest> totalordertest1 = getAll(totalordertest.class);
				for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setFull(1+totalordertest2.getFull());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId, TypeOfOrder, EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear, ParkingLotId, PersonID, Password,EnterMin,ExitMin);
				//NewOrder.setParkinglot(park1);
				//////////////////////////////////////
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				///////////////////////////////
				NewOrder.setSubId(PersonID);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

			/*	Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(TotalHours);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			if (FlagSubscriber == 0)
				message.setObject1("The Subscription Number is Wrong");
			else
			if (FlagEmptySpot == 0)
				message.setObject1("The ParkingLot is Full");
			else
			if (FlagRemainingHours == 0)
				message.setObject1("You Don't Have Enough Hours");

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (msgString.equals("#FullSubscriberOrder_enter")){


			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("FullSubscriberOrder_enter");
			Message cpymsg = ((Message) msg);
			System.out.println(cpymsg.getObject14().toString());
			String TypeOfOrder = "FullSubscriberOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<FullSubscriber> ListFullSubscriber = getAll(FullSubscriber.class);
			List<Order> ListOrders = getAll(Order.class);
			/********************************************************************/
			List<totalordertest> totalordertest1 = getAll(totalordertest.class);
			/********************************************************************/
			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour = Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay = Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth = Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear = Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour = Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay = Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth = Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear = Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId = Integer.parseInt(ParkingLotIdString);

			String PersonIDString = cpymsg.getObject10().toString();
			int PersonID = Integer.parseInt(PersonIDString);

			String Password = cpymsg.getObject11().toString();

			String SubscriptionIdString = cpymsg.getObject12().toString();
			int SubscriptionId = Integer.parseInt(SubscriptionIdString);

			boolean OnSite = (boolean)cpymsg.getObject13();

			String CarNumberString = cpymsg.getObject14().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject15().toString();
			System.out.println(Email);
			int TotalHours = HoursBetweenDates(EnterHour ,EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear);
			String ExitMint = cpymsg.getObject18().toString();
			int ExitMin = Integer.parseInt(ExitMint);

			String EnterMint = cpymsg.getObject17().toString();
			int EnterMin = Integer.parseInt(EnterMint);
			int Payment=TotalHours;
			int NumberOfOrdersInTheSameHours = 0;
			int MaxOrderId = 0;
			int FlagRemainingHours = 0;
			int FlagSubscriber = 0;
			int FlagEmptySpot = 0;
			int FlagOrder = 0;

			ParkingLot park1 = null;

			for (FullSubscriber Subscriber : ListFullSubscriber) {
				if (Subscriber.getSubscriptionId() == SubscriptionId) {
					FlagSubscriber = 1;
					for (ParkingLot ParkingLot : ListParkingLots) {
						//Check which ParkingLot in ListParkingLots has the same Id
						if (ParkingLot.getId() == ParkingLotId) {
							park1 = ParkingLot;
							for (Order Order : ListOrders) {
								//go through all orders in same Parking Lot
								if (Order.getParkingLotId() == ParkingLotId) {
									//check how many orders between the two dates
									if (Order.getEnterYear() <= ExitYear && Order.getExitYear() >= EnterYear) {
										if (Order.getEnterMonth() <= ExitMonth && Order.getExitMonth() >= EnterMonth) {
											if (Order.getEnterDay() <= ExitDay && Order.getExitDay() >= EnterDay) {
												if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
													if (Order.getEnterMinute() <= ExitMin && Order.getExitMinute() >= EnterMin) {
														//sum all orders in same parking lot in same hours
														NumberOfOrdersInTheSameHours++;
													}
												}
											}
										}
									}
								}
							}
/*		if(OnSite==true) {
								int numberAlreadyParkingLot = 0;
								for (Order order1 : ListOrders) {

									if (order1.getParkingLotId() == ParkingLotId) {
										if (order1.isAlreadyInParkingLot()) {
											numberAlreadyParkingLot += 1;
										}
									}
								}
								if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot > 0) {
									FlagEmptySpot = 1;
									//check if subscriber have enough hours
									if (Subscriber.getRemainingHours() >= TotalHours) {
										FlagRemainingHours = 1;
										//go through all orders for this person and check if he got two orders in same day
										for (Order Order : ListOrders) {
											if (Order.getPersonId() == PersonID) {
												if (Order.getEnterDay() == EnterDay) {
													FlagDay = 0;
												}
											}
										}
									}
								}
							}*/
							if(OnSite==true) {
								int numberAlreadyParkingLot = 0;
								for (Order order1 : ListOrders) {

									if (order1.getParkingLotId() == ParkingLotId) {
										if (order1.isAlreadyInParkingLot()) {
											numberAlreadyParkingLot += 1;
										}
									}
								}
								if (ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0 &&ParkingLot.getCapacity()- ParkingLot.getNumberOfInactiveParkings() - numberAlreadyParkingLot>0 ) {
									FlagEmptySpot = 1;
									//check if subscriber have enough hours
									if (Subscriber.getRemainingHours() >= TotalHours) {
										FlagRemainingHours = 1;
									}
								}
							}
							//check if there's empty spots
							else if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0) {
								FlagEmptySpot = 1;
								//check if subscriber have enough hours
								if (Subscriber.getRemainingHours() >= TotalHours) {
									FlagRemainingHours = 1;
								}
							}
						}
					}
				}
			}

			if (FlagRemainingHours == 1 && FlagEmptySpot == 1 && FlagSubscriber == 1)
				FlagOrder = 1;

			if (FlagOrder == 1) {
				for (Order Order : ListOrders) {
					if (Order.getOrderId() > MaxOrderId)
						MaxOrderId = Order.getOrderId();
				}
				MaxOrderId++;
				for (FullSubscriber Subscriber : ListFullSubscriber)
				{
					if (Subscriber.getId() == PersonID) {
						Subscriber.setRemainingHours(Subscriber.getRemainingHours() - TotalHours);
						session.update(Subscriber);
					}
				}
				/********************************************************************/
			    for (totalordertest totalordertest2:totalordertest1)
				{
					if (totalordertest2.getId()==ParkingLotId)
					{
						System.out.println("id is +"+ totalordertest2.getId());
						totalordertest2.setFull(1+totalordertest2.getFull());
						session.update(totalordertest2);
					}
				}
				/********************************************************************/
				Order NewOrder = new Order(MaxOrderId, TypeOfOrder, EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear, ParkingLotId, PersonID, Password,EnterMin,ExitMin);
				//NewOrder.setParkinglot(park1);
				//////////////////////////////////////
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, EnterMin);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, ExitMin);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				///////////////////////////////
				NewOrder.setSubId(PersonID);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

				/*Car NewCar = new Car(CarNumber);
				session.save(NewCar);*/

			/*	if(OnSite==true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);
*/
				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(TotalHours);

				for (ParkingLot ParkingLot:ListParkingLots)
				{
					if(ParkingLot.getId()==ParkingLotId)
					{
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders()+1);
						session.update(ParkingLot);
					}
				}
			}

			if (FlagSubscriber == 0)
				message.setObject1("The Subscription Number is Wrong");
			else
			if (FlagEmptySpot == 0) {
				message.setObject1("The ParkingLot is Full");
				try {
					ArrayList<Integer> hii=new ArrayList<Integer>();
					for (ParkingLot hi : getAll(ParkingLot.class)) {
						System.out.println(hi.getId()+" "+hi.getCapacity()+" "+hi.getEmptySpots()+" "+hi.getNumberOfInactiveParkings());
						if (hi.getEmptySpots()>0)
						{
							System.out.println("hii is  "+ hi.getId());
							hii.add(hi.getId());
						}
					}
					client.sendToClient(new Message("#SendToAlternative",hii));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			if (FlagRemainingHours == 0)
				message.setObject1("You Don't Have Enough Hours");

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		*************************************************************************************
//		*************************************************************************************

		if (msgString.equals("#checkifneedtorenewsubs")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("checkifneedtorenewsubs");
			Message cpymsg=((Message) msg);

			List<FullSubscriber> listfull = getAll(FullSubscriber.class);
			String subs = cpymsg.getObject1().toString();
			int subscribid=Integer.parseInt(subs);
			Date x=new Date(2023,2,2);
			System.out.println(x);
			for (FullSubscriber t:listfull)
			{
				if (t.getSubscriberId()==subscribid)
				{
					Date xx=new Date(2023,t.getSubscriptionExpiryDate().getMonthValue()-1,t.getSubscriptionExpiryDate().getDayOfMonth());
					System.out.println(xx);

					int diff = x.compareTo(xx);
					if (diff>0)
					{
						message.setObject8("renew");
					}
					else
					{
						message.setObject8("ok");
					}
				}
			}

			System.out.println(message.getMessage().toString());
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}


		if (msgString.equals("#InvalidSpotsReport")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("InvalidSpotsReport");
			Message cpymsg=((Message) msg);

			List<inactivenumber> listparkinglot = getAll(inactivenumber.class);

			String ParkIDString = cpymsg.getObject1().toString();
			int ParkID=Integer.parseInt(ParkIDString);
			String fromyea = cpymsg.getObject2().toString();
			int fromyear=Integer.parseInt(fromyea);
			String frommont = cpymsg.getObject3().toString();
			int frommonth=Integer.parseInt(frommont);
			String fromda = cpymsg.getObject4().toString();
			int fromday=Integer.parseInt(fromda);

			String toyea = cpymsg.getObject5().toString();
			int toyear=Integer.parseInt(toyea);
			String tomont = cpymsg.getObject6().toString();
			int tomonth=Integer.parseInt(tomont);
			String toda = cpymsg.getObject7().toString();
			int today=Integer.parseInt(toda);
			Date datefrom=new Date(fromyear,frommonth,fromday);
			Date dateto=new Date(toyear,tomonth,today);
			int InActiveSpots=0;

			for (inactivenumber parkinglot1 : listparkinglot) {
				if(parkinglot1.getParkingLotId() == ParkID )
				{
					Date datesql=new Date(parkinglot1.getDate().getYear(),parkinglot1.getDate().getMonthValue(),parkinglot1.getDate().getDayOfMonth());
					if ((datefrom.compareTo(datesql)<0&&dateto.compareTo(datesql)>0)||(datefrom.compareTo(datesql)==0||dateto.compareTo(datesql)==0))
					{
						InActiveSpots++;
					}
				}
			}
			System.out.println("InActiveSpots  "+InActiveSpots);
			message.setObject1(InActiveSpots);
			message.setObject2(ParkID);


			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#ComplaintsReport")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("ComplaintsReport");
			Message cpymsg=((Message) msg);

			List<Complaint> listparkinglot = getAll(Complaint.class);

			String ParkIDString = cpymsg.getObject1().toString();
			int ParkID=Integer.parseInt(ParkIDString);
			String fromyea = cpymsg.getObject2().toString();
			int fromyear=Integer.parseInt(fromyea);
			String frommont = cpymsg.getObject3().toString();
			int frommonth=Integer.parseInt(frommont);
			String fromda = cpymsg.getObject4().toString();
			int fromday=Integer.parseInt(fromda);

			String toyea = cpymsg.getObject5().toString();
			int toyear=Integer.parseInt(toyea);
			String tomont = cpymsg.getObject6().toString();
			int tomonth=Integer.parseInt(tomont);
			String toda = cpymsg.getObject7().toString();
			int today=Integer.parseInt(toda);


			int NumberOfComplaints=0;
			Date datefrom=new Date(fromyear,frommonth,fromday);
			Date dateto=new Date(toyear,tomonth,today);


			for (Complaint parkinglot1 : listparkinglot) {
				if (parkinglot1.getParkingLotId()==ParkID){
					Date datesql=new Date(parkinglot1.getDate().getYear(),parkinglot1.getDate().getMonthValue(),parkinglot1.getDate().getDayOfMonth());
					if ((datefrom.compareTo(datesql)<0&&dateto.compareTo(datesql)>0)||(datefrom.compareTo(datesql)==0||dateto.compareTo(datesql)==0))
					{
						NumberOfComplaints++;
					}
				}

			}
			//System.out.println(NumberOfComplaints);
			message.setObject1(NumberOfComplaints);
			message.setObject2(ParkID);


			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#displayreportofchain_COMPLAIN")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("displayreportofchain_COMPLAIN");
			Message cpymsg=((Message) msg);

			List<Complaint> listparkinglot = getAll(Complaint.class);
			List<Complaint> complaints = new ArrayList<Complaint>();

			String ParkIDString = cpymsg.getObject1().toString();
			int ParkID=Integer.parseInt(ParkIDString);
			String fromyea = cpymsg.getObject2().toString();
			int fromyear=Integer.parseInt(fromyea);
			String frommont = cpymsg.getObject3().toString();
			int frommonth=Integer.parseInt(frommont);
			String fromda = cpymsg.getObject4().toString();
			int fromday=Integer.parseInt(fromda);

			String toyea = cpymsg.getObject5().toString();
			int toyear=Integer.parseInt(toyea);
			String tomont = cpymsg.getObject6().toString();
			int tomonth=Integer.parseInt(tomont);
			String toda = cpymsg.getObject7().toString();
			int today=Integer.parseInt(toda);
			String h = cpymsg.getObject8().toString();
			double hh=Double.parseDouble(h);

			double NumberOfComplaints=0;
			Date datefrom=new Date(fromyear,frommonth,fromday);
			Date dateto=new Date(toyear,tomonth,today);


			for (Complaint parkinglot1 : listparkinglot) {
				if (parkinglot1.getParkingLotId()==ParkID){
					Date datesql=new Date(parkinglot1.getDate().getYear(),parkinglot1.getDate().getMonthValue(),parkinglot1.getDate().getDayOfMonth());
					if ((datefrom.compareTo(datesql)<0&&dateto.compareTo(datesql)>0)||(datefrom.compareTo(datesql)==0||dateto.compareTo(datesql)==0))
					{
						NumberOfComplaints++;
						complaints.add(parkinglot1);
					}
				}

			}
			//System.out.println(NumberOfComplaints);
			message.setObject1(complaints);
			System.out.println("NumberOfComplaints  "+NumberOfComplaints);
			System.out.println("diff day is "+hh);
			System.out.println("avg is "+(NumberOfComplaints/hh));
			message.setObject2(NumberOfComplaints);
			message.setObject3(hh);
			message.setObject4((NumberOfComplaints/hh));
			message.setObject5(ParkID);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#OrdersReport")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("OrdersReport");
			Message cpymsg=((Message) msg);
			List<Order> complaints = new ArrayList<Order>();
			List<Order> listordertype = getAll(Order.class);

			String ParkIDString = cpymsg.getObject1().toString();
			int ParkID=Integer.parseInt(ParkIDString);
			String fromyea = cpymsg.getObject2().toString();
			int fromyear=Integer.parseInt(fromyea);
			String frommont = cpymsg.getObject3().toString();
			int frommonth=Integer.parseInt(frommont);
			String fromda = cpymsg.getObject4().toString();
			int fromday=Integer.parseInt(fromda);

			String toyea = cpymsg.getObject5().toString();
			int toyear=Integer.parseInt(toyea);
			String tomont = cpymsg.getObject6().toString();
			int tomonth=Integer.parseInt(tomont);
			String toda = cpymsg.getObject7().toString();
			int today=Integer.parseInt(toda);

			Date datefrom=new Date(fromyear,frommonth,fromday);
			Date dateto=new Date(toyear,tomonth,today);

			int NumberOfOrders=0;
			int c1=0,c2=0,c3=0,c4=0;

			for (Order order1 : listordertype) {
				if (order1.getParkingLotId() == ParkID) {
					Date datesql = new Date(order1.getEnterYear(), order1.getEnterMonth(), order1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {
						if (order1.getTypeOfOrder().equals("FullSubscriberOrder")) {
							c1++;
							complaints.add(order1);
						}

					}
				}
			}
			for (Order order1 : listordertype) {
				if (order1.getParkingLotId() == ParkID) {
					Date datesql = new Date(order1.getEnterYear(), order1.getEnterMonth(), order1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {

						if (order1.getTypeOfOrder().equals("GuestOnSiteOrder")) {
							c3++;
							complaints.add(order1);
						}

					}
				}
			}
			for (Order order1 : listordertype) {
				if (order1.getParkingLotId() == ParkID) {
					Date datesql = new Date(order1.getEnterYear(), order1.getEnterMonth(), order1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {
						if (order1.getTypeOfOrder().equals("GuestPreOrder")) {
							c2++;
							complaints.add(order1);
						}
					}
				}
			}
			for (Order order1 : listordertype) {
				if (order1.getParkingLotId() == ParkID) {
					Date datesql = new Date(order1.getEnterYear(), order1.getEnterMonth(), order1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {
						if (order1.getTypeOfOrder().equals("RegularSubscriberOrder")) {
							c4++;
							complaints.add(order1);
						}
					}
				}
			}
			message.setObject2(ParkID);
			message.setObject3(c1);
			message.setObject4(c2);
			message.setObject5(c3);
			message.setObject6(c4);
			message.setObject7(complaints);

			System.out.println("c1");
			System.out.println(c1+" "+c2+" "+c3+" "+c4);
			int y=c1+c2+c3+c4;
			String yy="";
			yy+=y;
			message.setObject8(yy);

			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		//*******************************************************
		//*******************************************************

		if (msgString.equals("#Stats")) {

			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("hi1");
			Message message = new Message("Stats");
			Message cpymsg = ((Message) msg);


			List<Order> ordererer = getAll(Order.class);
			List<CanceledOrder> CanceledOrderordererer = getAll(CanceledOrder.class);
			String ParkIDString = cpymsg.getObject1().toString();
			int ParkID=Integer.parseInt(ParkIDString);
			String fromyea = cpymsg.getObject2().toString();
			int fromyear=Integer.parseInt(fromyea);
			String frommont = cpymsg.getObject3().toString();
			int frommonth=Integer.parseInt(frommont);
			String fromda = cpymsg.getObject4().toString();
			int fromday=Integer.parseInt(fromda);

			String toyea = cpymsg.getObject5().toString();
			int toyear=Integer.parseInt(toyea);
			String tomont = cpymsg.getObject6().toString();
			int tomonth=Integer.parseInt(tomont);
			String toda = cpymsg.getObject7().toString();
			int today=Integer.parseInt(toda);

			int c1=0;int c2=0;
			int c3=0;
			Date datefrom=new Date(fromyear,frommonth,fromday);
			Date dateto=new Date(toyear,tomonth,today);

			for (Order parkinglot1 : ordererer) {
				if (parkinglot1.getParkingLotId() == ParkID) {
					Date datesql = new Date(parkinglot1.getEnterYear(), parkinglot1.getEnterMonth(), parkinglot1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {
						c1++;
						if (parkinglot1.getLateArrival()==1)
						{
							c3++;
						}
					}

				}
			}
			for (CanceledOrder parkinglot1 : CanceledOrderordererer) {
				if (parkinglot1.getParkingLotId() == ParkID) {
					Date datesql = new Date(parkinglot1.getEnterYear(), parkinglot1.getEnterMonth(), parkinglot1.getEnterDay());
					if ((datefrom.compareTo(datesql) < 0 && dateto.compareTo(datesql) > 0) || (datefrom.compareTo(datesql) == 0 || dateto.compareTo(datesql) == 0)) {
						c2++;
					}
				}
			}

			try {
				message.setObject1(ParkID);
				message.setObject2(c1);
				message.setObject3(c2);
				message.setObject4(c3);
				client.sendToClient(message);
			}	 catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}




//		*************************************************************************************
//		*************************************************************************************

		if (msgString.startsWith("#Managerprices_")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Prices> parks = getAll(Prices.class);
			Message message = new Message("showpricefunManager");
			String t = String.valueOf(msgString.charAt(15));
			String m = "";
			String[] mm = {"", "", ""};
			int i = 0;
			for (Prices p : parks) {
				if (p.getId() == Integer.parseInt(t)) {
					message.setObject4(p.getValueNote().get(0));
					message.setObject2(p.getValueNote().get(1));
					m = parks.get(i).getPaymentMethod().get(0);
					mm[0] = m;
					m += ",";
					m += parks.get(i).getPaymentMethod().get(1);
					mm[1] = parks.get(i).getPaymentMethod().get(1);
					m += ",";
					m += parks.get(i).getPaymentMethod().get(2);
					mm[2] = parks.get(i).getPaymentMethod().get(2);
					message.setObject3(mm);
					message.setObject5(p.getValueNote().get(2));
					message.setObject6(p.getValueNote().get(3));
					message.setObject7(p.getValueNote().get(4));
				}
			}
			message.setObject1(t);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.startsWith("#UpdateNewPrices")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message newPriceData = (Message) msg;
			//int i = Integer.parseInt(newPriceData.getObject1().toString()) - 1;
			int j = Integer.parseInt(newPriceData.getObject6().toString()) - 1;
			List<Prices> myPrices = getAll(Prices.class);
			List<String> newPrice = myPrices.get(j).getNewValueNote();
			List<String> oldPrice = myPrices.get(j).getValueNote();

			/*
			System.out.println(oldPrice.get(0));
			System.out.println(oldPrice.get(1));
			System.out.println(oldPrice.get(2));
			System.out.println(oldPrice.get(3));
			System.out.println(oldPrice.get(4));
			System.out.println(newPriceData.getObject1().toString());
			System.out.println(newPriceData.getObject2().toString());
			System.out.println(newPriceData.getObject3().toString());
			System.out.println(newPriceData.getObject4().toString());
			System.out.println(newPriceData.getObject5().toString());
*/

			if (newPriceData.getObject1().toString().equals("1")) {
				myPrices.get(j).getValueNote().set(0, newPrice.get(0));
			}
			if (newPriceData.getObject2().toString().equals("1")) {
				myPrices.get(j).getValueNote().set(1, newPrice.get(1));
			}
			if (newPriceData.getObject3().toString().equals("1")) {
				myPrices.get(j).getValueNote().set(2, newPrice.get(2));
			}
			if (newPriceData.getObject4().toString().equals("1")){
				myPrices.get(j).getValueNote().set(3, newPrice.get(3));
			}
			if (newPriceData.getObject5().toString().equals("1")){
				myPrices.get(j).getValueNote().set(4, newPrice.get(4));
			}
/*
			System.out.println(oldPrice.get(0));
			System.out.println(oldPrice.get(1));
			System.out.println(oldPrice.get(2));
			System.out.println(oldPrice.get(3));
			System.out.println(oldPrice.get(4));*/
			for (int k=0; k<5 ; k++)
				myPrices.get(j).getNewValueNote().set(k, "-1");

			//myPrices.getNewValueNote().set(i, "new value");
			//session.save(myPrices.get(i).getNewValueNote());
			//session.save(myPrices.get(i).getValueNote());
			//session.update(myPrices);
			session.update(myPrices.get(j));
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.startsWith("#getOldPrice")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("123");
			Message newPriceData = (Message) msg;
			int i = Integer.parseInt(newPriceData.getObject1().toString()) - 1;
			List<Prices> myPrices = getAll(Prices.class);
			Message message = new Message("#returnOldPrice");
			if (i >= myPrices.size()){
				System.out.println("1");
				message.setObject1("error");
			}
			else {
				List<String> oldPrice = myPrices.get(i).getValueNote();
				System.out.println("2");
				message.setObject1("old");
				message.setObject2(oldPrice.get(0));
				message.setObject3(oldPrice.get(1));
				message.setObject4(oldPrice.get(2));
				message.setObject5(oldPrice.get(3));
				message.setObject6(oldPrice.get(4));
			}
			System.out.println("3");
			try {
				client.sendToClient(message);
				System.out.println("4");
			} catch (IOException e) {
				System.out.println("5");
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.startsWith("#getNewPrice")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			System.out.println("123");
			Message newPriceData = (Message) msg;
			int i = Integer.parseInt(newPriceData.getObject1().toString()) - 1;
			List<Prices> myPrices = getAll(Prices.class);
			Message message = new Message("#returnNewPrice");
			if (i >= myPrices.size()){
				System.out.println("1");
				message.setObject1("error1");
			}
			else {
				List<String> newPrice = myPrices.get(i).getNewValueNote();
				System.out.println("2");
				message.setObject1("new");
				message.setObject2(newPrice.get(0));
				//newPrice.set(1,"3");
				message.setObject3(newPrice.get(1));
				//message.setObject3("3");
				message.setObject4(newPrice.get(2));
				//newPrice.set(3,"5");
				message.setObject5(newPrice.get(3));
				message.setObject6(newPrice.get(4));
			}
			System.out.println("3");
			try {
				client.sendToClient(message);
				System.out.println("4");
			} catch (IOException e) {
				System.out.println("5");
				e.printStackTrace();
			}
			session.update(myPrices.get(i));
			session.flush();
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.startsWith("#sendNewPrice")) {
			session = sessionFactory.openSession();
			session.beginTransaction();

			Message newPriceData = (Message) msg;
			int i = Integer.parseInt(newPriceData.getObject1().toString()) - 1;
			int j = 0;
			switch (newPriceData.getObject2().toString()){
				case "On Site":
					j = 0;
					break;
				case "Preorder":
					j = 1;
					break;
				case "Client for One Car":
					j = 2;
					break;
				case "Client for a Number of Cars":
					j = 3;
					break;
				case "Full Subscription":
					j = 4;
					break;
				default:
					break;
			}
			List<Prices> myPrices = getAll(Prices.class);
			myPrices.get(i).getNewValueNote().set(j, newPriceData.getObject3().toString());

			//myPrices.getNewValueNote().set(i, "new value");
			session.update(myPrices.get(i));
			session.flush();
			session.getTransaction().commit();
			session.close();
		}
		//*************************************************************************************************
		//*************************************************************************************************
		if (msgString.startsWith("prices_")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Prices> parks = getAll(Prices.class);
			Message message = new Message("showpricefun2");
			String t = String.valueOf(msgString.charAt(7));
			String m = "";
			String[] mm = {"", "", ""};
			int i = 0;
			for (Prices p : parks) {
				if (p.getId() == Integer.parseInt(t)) {
					message.setObject4(p.getValueNote().get(0));
					message.setObject2(p.getValueNote().get(1));
					m = parks.get(i).getPaymentMethod().get(0);
					mm[0] = m;
					m += ",";
					m += parks.get(i).getPaymentMethod().get(1);
					mm[1] = parks.get(i).getPaymentMethod().get(1);
					m += ",";
					m += parks.get(i).getPaymentMethod().get(2);
					mm[2] = parks.get(i).getPaymentMethod().get(2);
					message.setObject3(mm);
					message.setObject5(p.getValueNote().get(2));
					message.setObject6(p.getValueNote().get(3));
					message.setObject7(p.getValueNote().get(4));
				}
			}
			message.setObject1(t);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#needmycars"))
		{

			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("#needmycars");
			Message msg1 = ((Message) msg);
			List<Car> hhh = getAll(Car.class);
			int x=0;
			List<String> carlist= new ArrayList<>();
			System.out.println("server_car1");
			for (Car h : hhh) {
				if (h.getFullsubscriber() != null) {

					if (msg1.getObject2().toString().equals("Full") && h.getFullsubscriber().getId() == Integer.parseInt(msg1.getObject1().toString())) {
						System.out.println("server");

						String tmp = "";
						tmp += h.getCarNumber();
						carlist.add((tmp));
					}
				}
				if (h.getRegularsubscriber() != null) {
					if ( msg1.getObject2().toString().equals("Regular")&&h.getRegularsubscriber().getId() ==Integer.parseInt( msg1.getObject1().toString())) {
						System.out.println("server");

						String tmp = "";
						tmp += h.getCarNumber();
						carlist.add((tmp));
					}
				}
			}
			System.out.println("server_car2");
			message.setObject1(carlist);
			System.out.println("server_car3");
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#loginadmin")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("loginadmin");
			List<Admin> listadmin = getAll(Admin.class);
			List<ChainManager> chain = getAll(ChainManager.class);
			List<ParkingLotEmployee> employeadmin = getAll(ParkingLotEmployee.class);
			List<ParkingLotManager> listadmin2 = getAll(ParkingLotManager.class);
			List<Complaint> nod = getAll(Complaint.class);
			Message msg1 = ((Message) msg);
			int c = 0;
			int c2 = 0;
			int twoconnectedclients=0;
			for (Admin p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients = -1;
				}
				if (p.getIsConnected().equals("0") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					twoconnectedclients = 1;
					message.setObject1("yes Customer Service");

				}
			}
			for (ChainManager p:chain){
				String tmp = "";

				tmp += p.getId();
				if (p.getIsConnected().equals("1") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
				}
				if (p.getIsConnected().equals("0")&& tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					message.setObject1("yes Chain Manager");
					message.setObject10(p.getId());
					message.setObject11(p.getFirstName());
					message.setObject12(p.getLastName());
					message.setObject13(p.getEmail());
				}

			}
			for (ParkingLotManager p : listadmin2) {
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1")&& tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
					System.out.println("aaa");
				}
				if ( p.getIsConnected().equals("0")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					twoconnectedclients=1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					message.setObject1("yes parkinglotmanagers");
					message.setObject2(p.getParkinglot().getId());
					message.setObject5(p.getEmail());
					message.setObject6(p.getFirstName());
					message.setObject7(p.getLastName());
					message.setObject8(p.getParkinglot().getId());
				}
			}


			for (ParkingLotEmployee p : employeadmin) {
				System.out.println("azzzz");
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1")&& tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
					System.out.println("aaa");
				}
				if ( p.getIsConnected().equals("0")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					twoconnectedclients=1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					message.setObject1("yes parkinglotemployee");
					message.setObject2(p.getParkinglot().getId());
					message.setObject5(p.getEmail());
					message.setObject6(p.getFirstName());
					message.setObject7(p.getLastName());
					message.setObject8(p.getParkinglot().getId());


				}
			}
			int l=0;
			for (Complaint node:nod)
			{
				if (node.getStatus().equals("no response yet"))
				{
					l++;
				}
			}
			message.setObject14(l);
			if(twoconnectedclients==0)
			{
				message.setObject1("no");
			}

			if(twoconnectedclients==-1)
			{
				message.setMessage("2clients");
				message.setObject1("2clients");
			}
			try {
				System.out.println(message.getObject1().toString());
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#logoutsubscirber")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("logoutsubscirber");
			List<FullSubscriber> listadmin = getAll(FullSubscriber.class);
			List<RegularSubscriber> listadmin2 = getAll(RegularSubscriber.class);
			Message msg1 = ((Message) msg);
			for (FullSubscriber p : listadmin) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}
			for (RegularSubscriber p : listadmin2) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}

			session.getTransaction().commit();
			session.close();
		}



		if (msgString.equals("#logoutlotemployee")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("logoutlotmanager");
			List<ParkingLotEmployee> listadmin = getAll(ParkingLotEmployee.class);
			Message msg1 = ((Message) msg);
			for (ParkingLotEmployee p : listadmin) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}
			session.getTransaction().commit();
			session.close();

		}


		if (msgString.equals("#total_order_number")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("total_order_number");
			Message cpymsg=((Message) msg);

			List<Complaint> tmp = getAll(Complaint.class);
			List<CanceledOrder> tmp2 = getAll(CanceledOrder.class);
			List<Order> tmp3 = getAll(Order.class);
			List<totalordertest> tmp4 = getAll(totalordertest.class);
			List<Integer> parkinglot_order1 = new ArrayList<>();
			List<Integer> parkinglot_order2 = new ArrayList<>();
			List<Integer> parkinglot_order3 = new ArrayList<>();
			List<Integer> parkinglot_order4 = new ArrayList<>();
			List<Integer> parkinglot_order5 = new ArrayList<>();
			List<Integer> parkinglot_order6 = new ArrayList<>();
			List<Integer> parkinglot_order7 = new ArrayList<>();
			int c=0;
			for (Order x : tmp3) {
				c++;
			}
			message.setObject4(c);
			int c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==1)
				{
					c2++;
					parkinglot_order1.add(tete.getFull());
					parkinglot_order1.add(tete.getGuest());
					parkinglot_order1.add(tete.getReg());

				}
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==2)
				{
					c2++;
					parkinglot_order2.add(tete.getFull());
					parkinglot_order2.add(tete.getGuest());
					parkinglot_order2.add(tete.getReg());

				}c2++;
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==3)
				{
					c2++;
					parkinglot_order3.add(tete.getFull());
					parkinglot_order3.add(tete.getGuest());
					parkinglot_order3.add(tete.getReg());

				}c2++;
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==4)
				{
					c2++;
					parkinglot_order4.add(tete.getFull());
					parkinglot_order4.add(tete.getGuest());
					parkinglot_order4.add(tete.getReg());

				}c2++;
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==5)
				{
					c2++;
					parkinglot_order5.add(tete.getFull());
					parkinglot_order5.add(tete.getGuest());
					parkinglot_order5.add(tete.getReg());

				}c2++;
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==6)
				{
					c2++;
					parkinglot_order6.add(tete.getFull());
					parkinglot_order6.add(tete.getGuest());
					parkinglot_order6.add(tete.getReg());

				}c2++;
			}
			c2=1;
			for (totalordertest tete:tmp4)
			{
				if (c2==7)
				{
					c2++;
					parkinglot_order7.add(tete.getFull());
					parkinglot_order7.add(tete.getGuest());
					parkinglot_order7.add(tete.getReg());

				}c2++;
			}
			c2=0;
			message.setObject9(parkinglot_order1);
			message.setObject10(parkinglot_order2);
			message.setObject11(parkinglot_order3);
			message.setObject12(parkinglot_order4);
			message.setObject13(parkinglot_order5);
			message.setObject14(parkinglot_order6);
			message.setObject15(parkinglot_order7);
			//System.out.println("Park "+j+"  "+ parkinglot_arrival);

			c=0;
			for (CanceledOrder x : tmp2) {
				c++;
			}
			message.setObject3(c);
			c=0;
			for (Complaint x : tmp) {
				c++;
			}
			System.out.println("aasdadadsada");
			message.setObject1("total_complains_number");
			message.setObject2(c);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#total_complains_number_init")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("total_complains_number_init");
			Message cpymsg=((Message) msg);
			int c=0;
			List<Complaint> com = getAll(Complaint.class);
			List<Order> ord = getAll(Order.class);
			List<CanceledOrder> canord = getAll(CanceledOrder.class);
			for(Complaint compl:com)
			{
				c++;
			}
			message.setObject1(c);
			c=0;
			for(Order ord1:ord)
			{
				c++;
			}
			message.setObject2(c);
			c=0;
			for(CanceledOrder ord11:canord)
			{
				c++;
			}
			message.setObject3(c);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#total_complains_number")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("total_complains_number");
			Message cpymsg=((Message) msg);

			List<Complaint> tmp = getAll(Complaint.class);
			int c=0;
			int c1,c2,c3,c4,c5,c6,c7=0;
			c1=c2=c3=c4=c5=c6=c7;
			for (Complaint x : tmp) {
				if (x.getParkingLotId()==1)
				{
					c1++;
				}
				if (x.getParkingLotId()==2)
				{
					c2++;
				}
				if (x.getParkingLotId()==3)
				{
					c3++;
				}
				if (x.getParkingLotId()==4)
				{
					c4++;
				}
				if (x.getParkingLotId()==5)
				{
					c5++;
				}
				if (x.getParkingLotId()==6)
				{
					c6++;
				}
				if (x.getParkingLotId()==7)
				{
					c7++;
				}
			}
			message.setObject1("total_complains_number");
			message.setObject2(c);
			message.setObject15(c1);
			message.setObject14(c2);
			message.setObject13(c3);
			message.setObject12(c4);
			message.setObject11(c5);
			message.setObject10(c6);
			message.setObject9(c7);
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (msgString.equals("#total_cancelorder_number")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("total_cancelorder_number");
			Message cpymsg=((Message) msg);
			List<CanceledOrder> tmp = getAll(CanceledOrder.class);
			List<totalordertest> pmt = getAll(totalordertest.class);
			int c=0;
			for (CanceledOrder tmp2:tmp)
			{
				c++;
			}
			int c2=0;
			for (totalordertest pmt2:pmt)
			{
				c2=c2+pmt2.getFull()+pmt2.getReg()+pmt2.getGuest();
			}
			message.setObject11(c);
			message.setObject12(c2);

			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#logoutlotmanager")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("logoutlotmanager");
			List<ParkingLotManager> listadmin = getAll(ParkingLotManager.class);
			Message msg1 = ((Message) msg);
			for (ParkingLotManager p : listadmin) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}
			session.getTransaction().commit();
			session.close();

		}

		if (msgString.equals("#writeareview")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			msg1.setMessage("writeareview");
			review l=new review(msg1.getObject1().toString(),msg1.getObject2().toString(),msg1.getObject3().toString());
			session.save(l);
			session.update(l);
			try {
				client.sendToClient(msg1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#review_0")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<review> listadmin = getAll(review.class);
			System.out.println("x.getId()");
			Message msg1 = ((Message) msg);
			msg1.setMessage("review_0");
			msg1.setObject7("no");

			for (review x:listadmin)
			{
				String tmp="";
				tmp+=x.getId();
				System.out.println("x.getId()");
				if(msg1.getObject1().equals(tmp))
				{
					msg1.setObject7("yes");
					msg1.setObject1(x.getName());
					msg1.setObject2(x.getStar());
					msg1.setObject3(x.getText());
				}
			}
			try {
				client.sendToClient(msg1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#review_-1")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<review> listadmin = getAll(review.class);
			Message msg1 = ((Message) msg);
			msg1.setMessage("review_-1");
			msg1.setObject7("no");
			for (review x:listadmin)
			{
				String tmp="";
				tmp+=x.getId();
				if(msg1.getObject1().equals(tmp))
				{
					msg1.setObject7("yes");
					msg1.setObject1(x.getName());
					msg1.setObject2(x.getStar());
					msg1.setObject3(x.getText());
				}
			}
			try {
				client.sendToClient(msg1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#logoutchain")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("logoutchain");
			List<ChainManager> listadmin = getAll(ChainManager.class);
			Message msg1 = ((Message) msg);
			for (ChainManager p : listadmin) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#logoutcusservices")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("logoutchain");
			List<Admin> listadmin = getAll(Admin.class);
			Message msg1 = ((Message) msg);
			for (Admin p : listadmin) {
				String tmp="";
				tmp+=p.getId();
				if(tmp.equals(msg1.getObject1()))
				{
					System.out.println(tmp+"logout succse");
					p.setIsConnected("0");
					session.save(p);
					session.update(p);
				}
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#loginsubscriber")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("loginsubscriber");
			List<FullSubscriber> listadmin = getAll(FullSubscriber.class);
			List<RegularSubscriber> listadmin2 = getAll(RegularSubscriber.class);

			Message msg1 = ((Message) msg);
			int c = 0;
			int c2 = 0;
			int twoconnectedclients=0;
			for (FullSubscriber p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
				}
				if (p.getIsConnected().equals("0")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					twoconnectedclients=1;
					message.setObject1("yes full_subscriber");
					message.setObject7(p.getFirstName());
					message.setObject8(p.getLastName());
					message.setObject9(p.getEmail());
				}
			}
			for (RegularSubscriber p : listadmin2) {
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
				}
				if (p.getIsConnected().equals("0")&&tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					twoconnectedclients=1;
					message.setObject1("yes regular_subscriber");
					message.setObject7(p.getFirstName());
					message.setObject8(p.getLastName());
					message.setObject9(p.getEmail());
				}
			}

			if(twoconnectedclients==0)
			{
				message.setObject1("no");
			}

			if(twoconnectedclients==-1)
			{
				message.setMessage("2clients");
				message.setObject1("2clients");
			}
			try {
				System.out.println(message.getObject1().toString());
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#admin_forgetpass")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("admin_forgetpass");
			List<Admin> listadmin = getAll(Admin.class);
			Message msg1 = ((Message) msg);
			int c = 0;
			int c2 = 0;
			for (Admin p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (tmp.equals(msg1.getObject1()) && msg1.getObject2().equals(p.getFirstName()) && msg1.getObject3().equals(p.getLastName()) && msg1.getObject4().equals(p.getEmail())) {
					c = 1;
					message.setObject1("yes");
					message.setObject2("" + p.getId());
				}
			}
			if (c == 0) {
				message.setObject1("no");
			}
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.startsWith("#warning")) {
			Message message = new Message("Warning from server!");
			try {
				client.sendToClient(message);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if(msgString.equals("#addcar")){
			System.out.println("adddddadadadad");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Car> carList = getAll(Car.class);
			List<FullSubscriber> fullSubscriberList = getAll(FullSubscriber.class);
			List<RegularSubscriber> regularSubscriberList = getAll(RegularSubscriber.class);
			Message msg1 = ((Message) msg);
			int carNumber = ((int) msg1.getObject1());
			int sub_id = ((int) msg1.getObject2());
			for (Car car : carList){
				if (car.getCarNumber() == carNumber){
					try {
						client.sendToClient(new Message("#caralreadylinked"));
					}
					catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			Car car = new Car(carNumber);
			for (FullSubscriber fullSubscriber : fullSubscriberList) {
				if (fullSubscriber.getSubscriberId() == sub_id ) {
					fullSubscriber.addCar(car);
					car.setFullsubscriber(fullSubscriber);
					session.update(fullSubscriber);
				}
			}
			for (RegularSubscriber regularSubscriber : regularSubscriberList) {
				if (regularSubscriber.getSubscriberId() == sub_id) {
					regularSubscriber.addCar(car);
					car.setRegularsubscriber(regularSubscriber);
					session.update(regularSubscriber);
				}
			}
			session.save(car);
			session.flush();
			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(new Message("#caradded"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (msgString.equals("#removecar")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Car> carList = getAll(Car.class);
			List<FullSubscriber> fullSubscriberList = getAll(FullSubscriber.class);
			List<RegularSubscriber> regularSubscriberList = getAll(RegularSubscriber.class);
			Message msg1 = ((Message) msg);
			int carNumber = (int) msg1.getObject1();
			int sub_id = (int) msg1.getObject2();
			//String typeOfSub = (String) msg1.getObject3();
			boolean carExists = false;

			for (Car car : carList){
				if (car.getCarNumber() == carNumber){
					carExists = true;
					for (FullSubscriber fullSubscriber : fullSubscriberList) {
						if (fullSubscriber.getSubscriberId() == sub_id&&fullSubscriber.getCarList().contains(carNumber)) {
							fullSubscriber.removeCar(car);
							session.update(fullSubscriber);
						}
					}
					for (RegularSubscriber regularSubscriber : regularSubscriberList) {
						if (regularSubscriber.getSubscriberId() == sub_id&&regularSubscriber.getCarList().contains(carNumber)) {
							regularSubscriber.removeCar(car);
							session.update(regularSubscriber);
						}
					}
					session.delete(car);
				}
			}
			if (!carExists){
				try {
					client.sendToClient(new Message("#cardoesntexist"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {

				try {
					client.sendToClient(new Message("#carremoved"));
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			session.flush();
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#showparkinglotpicture")){
			System.out.println("imataparkingstatus");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			System.out.println(parkingLotId);
			for (ParkingLot parkingLot : parkingLotList){
				if (parkingLot.getId() == parkingLotId){
					int rows = parkingLot.getNumberOfRows();
					int columns = parkingLot.getNumberOfColumns();
					int depth = parkingLot.getDepth();
					System.out.println(columns);
					System.out.println(depth);
					byte[] matrix1d = parkingLot.getMatrix();
					int[][][] matrix3d = new int[rows][columns][depth];
					int index = 0;  // Index into 1D array
					for (int i = 0; i < rows; i++) {
						for (int j = 0; j < columns; j++) {
							for (int k = 0; k < depth; k++) {
								matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
								index++;
							}
						}
					}
					System.out.println("no1");
					try {
						System.out.println("yes1");
						client.sendToClient(new Message("#showparkinglotpicture" , matrix3d ,columns));
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			session.close();
		}
		if (msgString.equals("#enterparkinglot")) {

			System.out.println("enterrrrrparrk");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int carNumber = (int) msg1.getObject2();
			int idddd = (int) msg1.getObject3();
			System.out.println(parkingLotId);
			System.out.println(carNumber);
			System.out.println(idddd);
			boolean itshisorder = false;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			for (Order order : getAll(Order.class)) {
				if (order.getCarNumber() == carNumber && order.getParkingLotId() == parkingLotId && order.isAlreadyInParkingLot()) {
					try {
						client.sendToClient(new Message("#TheCarIsAlreadyIn"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					session.close();
					return;
				}
			}

			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {
					int counter = 0;
					List<Order> currentOrders = getAll(Order.class).stream()
							.filter(Order::isAlreadyInParkingLot)
							.filter(order -> order.getParkingLotId() == parkingLotId)
							.collect(Collectors.toList());

//					System.out.println("got to this point");
//					for (Order order : currentOrders) {
//						if (order.getCarNumber() == carNumber) {
//							hasOrder = true;
//						}
//						if (order.isAlreadyInParkingLot()) {
//							counter++;
//						}
//					}
//
//					System.out.println(getAll(Order.class).size());
//					System.out.println(hasOrder);
//					System.out.println(parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows());
//					System.out.println(currentOrders.size());
//					System.out.println(parkingLot.getNumberOfInactiveParkings());
//					System.out.println(counter);
//					System.out.println("]]]]]]]]]][[[[[[[[[");
					//in case the parking lot is full and he has no order
//					if (((parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows())
//							- currentOrders.size() - parkingLot.getNumberOfInactiveParkings() <= 0) && (hasOrder == false)) {
//						try {
//							client.sendToClient(new Message("#SendToAlternative"));
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
					//in case he has an order but the parking lot is full as well
					System.out.println("idddddddd        "+ idddd);
					for (Order order : getAll(Order.class)) {
						if (order.getCarNumber() == carNumber && order.getParkingLotId() == parkingLotId && order.getPersonId() == idddd) {
							System.out.println("hello friend");
							itshisorder = true;
							break;
						}
					}
					if (itshisorder == false) {
						System.out.println("hello____ friend");

						try {
							client.sendToClient(new Message("#CarIdNoOrder"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if (((parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows())
							- currentOrders.size() - parkingLot.getNumberOfInactiveParkings() <= 0)) {
						try {
							ArrayList<Integer> hii=new ArrayList<Integer>();
							for (ParkingLot hi : getAll(ParkingLot.class)) {
								if (hi.getCapacity()-(hi.getEmptySpots()+hi.getNumberOfInactiveParkings())>0)
								{
									hii.add(hi.getId());
								}
							}
							client.sendToClient(new Message("#SendToAlternative",hii));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("got to this point33");
						//the car enters the parking lot
						try {
							client.sendToClient(new Message("#CarEntered"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						for (Order order : getAll(Order.class)) {
							if (order.getCarNumber() == carNumber) {
								order.setAlreadyInParkingLot(true);
								LocalDateTime dateTime = LocalDateTime.parse(order.getEntryTime(), formatter);
								LocalDateTime now1 = LocalDateTime.now();
								dateTime.plusMinutes(5);
								if (dateTime.isBefore(now1)) {
									order.setLateArrival(1);
								}
								session.update(order);
							}
						}
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									if (matrix3d[i][j][k] != 2) {
										System.out.println(";;;;;;;; ");
										System.out.println(i);
										System.out.println(j);
										System.out.println(k);
										matrix3d[i][j][k] = 0;
									}
								}
							}
						}

						currentOrders = getAll(Order.class).stream()
								.filter(Order::isAlreadyInParkingLot)
								.filter(order -> order.getParkingLotId() == parkingLotId)
								.collect(Collectors.toList());

						Collections.sort(currentOrders, new Comparator<Order>() {
							@Override
							public int compare(Order o1, Order o2) {
								LocalDateTime date1 = LocalDateTime.parse(o1.getExitTime(), formatter);
								LocalDateTime date2 = LocalDateTime.parse(o2.getExitTime(), formatter);
								return date1.compareTo(date2);
							}
						});
						for (Order tmp: currentOrders)
						{
							//System.out.println("asasasas"+tmp.getCarNumber());
						}
						int depthIndex = 0;
						int rowIndex = 0;
						int colIndex = 0;
						for (Order order : currentOrders) {

							if (order.isAlreadyInParkingLot()) {
								System.out.println("order car num is   "+ order.getCarNumber());
								while (matrix3d[rowIndex][colIndex][depthIndex] == 2) {
									if (rowIndex == rows - 1 && colIndex == columns - 1) {
										depthIndex++;
										colIndex = 0;
										rowIndex = 0;
									} else if (colIndex == columns - 1) {
										rowIndex++;
										colIndex = 0;
									}
									colIndex++;
								}
								System.out.println("row col depth   "+ rowIndex+"  "+ colIndex+"  "+depthIndex);

								matrix3d[rowIndex][colIndex][depthIndex] = order.getCarNumber();
								if (colIndex==columns-1)
								{
									colIndex=0;
								}
								else {
									colIndex++;
								}
							}
						}

						int index2 = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
									index2++;
								}
							}
						}
						parkingLot.setEmptySpots(parkingLot.getEmptySpots() - 1);
						parkingLot.setMatrix(matrix1d);
						session.update(parkingLot);
					}
				}
			}
			session.getTransaction().commit();
			session.close();
		}
		/*if (msgString.equals("#enterparkinglot")){
			System.out.println("enterrrrrparrk");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int carNumber = (int) msg1.getObject2();
			System.out.println("lllllll   "+parkingLotId);
			System.out.println("ooooooo   "+carNumber);
			System.out.println("lllllll   "+msg1.getObject3());

			boolean hasOrder = false;
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {
					int counter = 0;
					List<Order> currentOrders = getAll(Order.class).stream()
							.filter(order -> (LocalDateTime.parse(order.getEntryTime(), formatter).isBefore(now) ||
									LocalDateTime.parse(order.getEntryTime(), formatter).isEqual(now))
									&& LocalDateTime.parse(order.getExitTime(), formatter).isAfter(now))
							.filter(order -> order.getParkingLotId() == 1)
							.collect(Collectors.toList());

					System.out.println("got to this point");
					for (Order order : getAll(Order.class)) {
						System.out.println("aaaaaa      "+order.getParkingLotId());
						System.out.println("gggg    "+LocalDateTime.parse(order.getEntryTime(), formatter).isBefore(now));
						System.out.println("hhh   "+LocalDateTime.parse(order.getExitTime(), formatter).isAfter(now));
						if (order.getCarNumber() == carNumber) {
							hasOrder = true;
						}
						if (order.isAlreadyInParkingLot()) {
							counter++;
						}
					}

					System.out.println(getAll(Order.class).size());
					System.out.println(hasOrder);
					System.out.println(parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows());
					System.out.println(currentOrders.size());
					System.out.println(parkingLot.getNumberOfInactiveParkings());
					System.out.println(counter);
					System.out.println("]]]]]]]]]][[[[[[[[[");
					//in case the parking lot is full and he has no order
					if (((parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows())
							- currentOrders.size() - parkingLot.getNumberOfInactiveParkings() <= 0) && (hasOrder == false)) {
						try {
							client.sendToClient(new Message("#SendToAlternative"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					//in case he has an order but the parking lot is full as well
					else if (((parkingLot.getDepth() * parkingLot.getNumberOfColumns() * parkingLot.getNumberOfRows())
							- counter - parkingLot.getNumberOfInactiveParkings() <= 0)) {
						try {
							client.sendToClient(new Message("#SendToAlternative"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("got to this point33");
						//the car enters the parking lot
						try {
							client.sendToClient(new Message("#CarEntered"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						for (Order order : getAll(Order.class)) {
							if (order.getCarNumber() == carNumber) {
								order.setAlreadyInParkingLot(true);
								session.update(order);
							}
						}
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									if (matrix3d[i][j][k] != 2) {
										System.out.println(";;;;;;;; " );
										System.out.println(i);
										System.out.println(j);
										System.out.println(k);
										matrix3d[i][j][k] = 0;
									}
								}
							}
						}

						currentOrders = getAll(Order.class).stream()
								.filter(order -> (LocalDateTime.parse(order.getEntryTime(), formatter).isBefore(now) ||
										LocalDateTime.parse(order.getEntryTime(), formatter).isEqual(now))
										&& LocalDateTime.parse(order.getExitTime(), formatter).isAfter(now))
								.filter(order -> order.getParkingLotId() == 1)
								.collect(Collectors.toList());

						Collections.sort(currentOrders, new Comparator<Order>() {
							@Override
							public int compare(Order o1, Order o2) {
								LocalDateTime date1 = LocalDateTime.parse(o1.getExitTime(), formatter);
								LocalDateTime date2 = LocalDateTime.parse(o2.getExitTime(), formatter);
								return date1.compareTo(date2);
							}
						});
						int depthIndex = 0;
						int rowIndex = 0;
						int colIndex = 0;
						for (Order order : currentOrders) {
							if (order.isAlreadyInParkingLot()) {
								while (matrix3d[rowIndex][colIndex][depthIndex] == 2) {
									if (rowIndex == rows - 1 && colIndex == columns - 1) {
										depthIndex++;
										colIndex = 0;
										rowIndex = 0;
									} else if (colIndex == columns - 1) {
										rowIndex++;
										colIndex = 0;
									}
									colIndex++;
								}
								matrix3d[rowIndex][colIndex][depthIndex] = order.getCarNumber();
							}
						}

						int index2 = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
									index2++;
								}
							}
						}
						parkingLot.setMatrix(matrix1d);
						session.update(parkingLot);
					}
				}
			}
			session.getTransaction().commit();
			session.close();
		}*/
		if (msgString.equals("#exitrparkinglot")) {
			System.out.println("okexit");
			if (session!=null){
				session = sessionFactory.openSession();
				session.beginTransaction();

			}
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int carNumber = (int) msg1.getObject2();
			int idddd = (int) msg1.getObject3();
			System.out.println(carNumber);
			boolean hasOrder = false;
			boolean itshisorder = false;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {

					System.out.println("fafaffa");
					List<Order> currentOrders = getAll(Order.class).stream()
							.filter(Order::isAlreadyInParkingLot)
							.filter(order -> order.getParkingLotId() == parkingLotId)
							.collect(Collectors.toList());

					System.out.println("lglaglaga");
					for (Order order : currentOrders) {
						if (order.getCarNumber() == carNumber && order.getParkingLotId() == parkingLotId) {
							hasOrder = true;
							break;
						}
					}
					System.out.println(hasOrder);
					if (hasOrder == false) {
						try {
							client.sendToClient(new Message("#CarIsNotInParkingLot"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					for (Order order : currentOrders) {
						if (order.getCarNumber() == carNumber && order.getParkingLotId() == parkingLotId && order.getPersonId() == idddd) {
							itshisorder = true;
							break;
						}
					}
					System.out.println(itshisorder);
					if (itshisorder == false) {
						try {
							client.sendToClient(new Message("#CarIsNotInParkingLot"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						try {
							String lala="";
							boolean late=false;
							int number=1;
							double price=-1;
							for (Order order : getAll(Order.class)) {
								if (order.getCarNumber() == carNumber && order.getTypeOfOrder().equals("GuestOnSiteOrder")) {
									lala=order.getEntryTime();
									if (order.getLateArrival()==1)late=true;
									LocalDateTime now2 = LocalDateTime.now();
									LocalDateTime otherDate = LocalDateTime.parse(lala, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
									Duration duration = Duration.between(otherDate,now2);
									//long hours = duration.toMinutes();

									long minutes = ChronoUnit.MINUTES.between(otherDate,now2);
									System.out.println("minutes before "+minutes);

									long hours = (long) Math.ceil((double) minutes / 60);
									System.out.println("hour before "+hours);
									for (Prices hola : getAll(Prices.class))
									{
										if (hola.getId()==order.getParkingLotId())
										{

											Pattern pattern = Pattern.compile("\\d+"); // regex to match one or more digits
											Matcher matcher = pattern.matcher(hola.getValueNote().get(1));
											if (matcher.find()) {
												String numberStr = matcher.group(); // extract the matched string
												 number = Integer.parseInt(numberStr); // convert string to integer
												System.out.println(number); // output the extracted number
											}

										}
									}
									 price=number*hours;
								}
								if (order.getCarNumber() == carNumber && order.getTypeOfOrder().equals("GuestPreOrder")) {
									lala=order.getEntryTime();
									if (order.getLateArrival()==1)late=true;
									LocalDateTime now2 = LocalDateTime.now();
									LocalDateTime otherDate = LocalDateTime.parse(lala, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
									Duration duration = Duration.between(otherDate,now2);
									//long hours = duration.toMinutes();

									long minutes = ChronoUnit.MINUTES.between(otherDate,now2);
									System.out.println("minutes before "+minutes);

									long hours = (long) Math.ceil((double) minutes / 60);
									System.out.println("hour before "+hours);
									for (Prices hola : getAll(Prices.class))
									{
										if (hola.getId()==order.getParkingLotId())
										{

											Pattern pattern = Pattern.compile("\\d+"); // regex to match one or more digits
											Matcher matcher = pattern.matcher(hola.getValueNote().get(0));
											if (matcher.find()) {
												String numberStr = matcher.group(); // extract the matched string
												number = Integer.parseInt(numberStr); // convert string to integer
												System.out.println(number); // output the extracted number
											}

										}
									}
									if (late==true)
									{
										price=number*hours;
										price=price*120/100;
									}
									else
									price=number*hours;
								}
							}
							String tmp="";
							tmp+=price;
							client.sendToClient(new Message("#CarExited",tmp));
						} catch (IOException e) {
							e.printStackTrace();
						}
						for (Order order : getAll(Order.class)) {
							if (order.getCarNumber() == carNumber) {
								session.delete(order);
							}
						}
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									if (matrix3d[i][j][k] != 2) {
										matrix3d[i][j][k] = 0;
									}
								}
							}
						}
						currentOrders = getAll(Order.class).stream()
								.filter(Order::isAlreadyInParkingLot)
								.filter(order -> order.getParkingLotId() == parkingLotId)
								.collect(Collectors.toList());


						Collections.sort(currentOrders, new Comparator<Order>() {
							@Override
							public int compare(Order o1, Order o2) {
								LocalDateTime date1 = LocalDateTime.parse(o1.getExitTime(), formatter);
								LocalDateTime date2 = LocalDateTime.parse(o2.getExitTime(), formatter);
								return date1.compareTo(date2);
							}
						});
						int depthIndex = 0;
						int rowIndex = 0;
						int colIndex = 0;
						for (Order order : currentOrders) {
							if (order.isAlreadyInParkingLot()) {
								while (matrix3d[rowIndex][colIndex][depthIndex] == 2) {
									if (rowIndex == rows - 1 && colIndex == columns - 1) {
										depthIndex++;
										colIndex = 0;
										rowIndex = 0;
									} else if (colIndex == columns - 1) {
										rowIndex++;
										colIndex = 0;
									}
									colIndex++;
								}
								matrix3d[rowIndex][colIndex][depthIndex] = order.getCarNumber();
								if (colIndex==columns-1)
								{
									colIndex=0;
								}
								else {
									colIndex++;
								}
							}

						}

						int index2 = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
									index2++;
								}
							}
						}
						parkingLot.setEmptySpots(parkingLot.getEmptySpots() + 1);
						parkingLot.setMatrix(matrix1d);
						session.update(parkingLot);

					}
				}

			}
			session.getTransaction().commit();
			session.close();

		}
	/*	if (msgString.equals("#exitrparkinglot")){
			System.out.println("okexit");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int carNumber = (int) msg1.getObject2();
			System.out.println(carNumber);
			boolean hasOrder = false;

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			for (ParkingLot parkingLot : parkingLotList){
				if (parkingLot.getId() == parkingLotId) {

					List<Order> currentOrders = getAll(Order.class).stream()
							.filter(order -> (LocalDateTime.parse(order.getEntryTime(), formatter).isBefore(now) ||
									LocalDateTime.parse(order.getEntryTime(), formatter).isEqual(now))
									&& LocalDateTime.parse(order.getExitTime(), formatter).isAfter(now))
							.filter(order -> order.getParkingLotId() == 1)
							.collect(Collectors.toList());

					for (Order order : currentOrders) {
						if (order.getCarNumber() == carNumber && order.isAlreadyInParkingLot() && order.getParkingLotId() == parkingLotId) {
							hasOrder = true;
							break;
						}
					}
					if (hasOrder == false) {
						try {
							client.sendToClient(new Message("#CarIsNotInParkingLot"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						try {
							client.sendToClient(new Message("#CarExited"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						for (Order order : getAll(Order.class)) {
							if (order.getCarNumber() == carNumber) {
								session.delete(order);
							}
						}
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									if (matrix3d[i][j][k] != 2) {
										matrix3d[i][j][k] = 0;
									}
								}
							}
						}
						currentOrders = getAll(Order.class).stream()
								.filter(order -> (LocalDateTime.parse(order.getEntryTime(), formatter).isBefore(now) ||
										LocalDateTime.parse(order.getEntryTime(), formatter).isEqual(now))
										&& LocalDateTime.parse(order.getExitTime(), formatter).isAfter(now))
								.filter(order -> order.getParkingLotId() == 1)
								.collect(Collectors.toList());


						Collections.sort(currentOrders, new Comparator<Order>() {
							@Override
							public int compare(Order o1, Order o2) {
								LocalDateTime date1 = LocalDateTime.parse(o1.getExitTime(), formatter);
								LocalDateTime date2 = LocalDateTime.parse(o2.getExitTime(), formatter);
								return date1.compareTo(date2);
							}
						});
						int depthIndex = 0;
						int rowIndex = 0;
						int colIndex = 0;
						for (Order order : currentOrders) {
							if (order.isAlreadyInParkingLot()) {
								while (matrix3d[rowIndex][colIndex][depthIndex] == 2) {
									if (rowIndex == rows - 1 && colIndex == columns - 1) {
										depthIndex++;
										colIndex = 0;
										rowIndex = 0;
									} else if (colIndex == columns - 1) {
										rowIndex++;
										colIndex = 0;
									}
									colIndex++;
								}
								matrix3d[rowIndex][colIndex][depthIndex] = order.getCarNumber();
							}
						}

						int index2 = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
									index2++;
								}
							}
						}
						parkingLot.setMatrix(matrix1d);
						session.update(parkingLot);
					}
				}

			}
			session.getTransaction().commit();
			session.close();

		}*/

		if (msgString.equals("#inactive")){
			System.out.println("wowinactive");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int parkingLotRow = (int) msg1.getObject2();
			int parkingLotCol = (int) msg1.getObject3();
			int parkingLotDepth = (int) msg1.getObject4();
			System.out.println(parkingLotDepth);
			System.out.println(parkingLotId);
			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {
					if (parkingLot.getNumberOfColumns()-1 < parkingLotCol || 0 > parkingLotCol){
						try {
							client.sendToClient(new Message("#NumberOfColIsWrong"));
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						if (matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] != 0 &&
								matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] != 2){
							try {
								client.sendToClient(new Message("#SomeoneIsParked"));
							}
							catch (IOException e) {
								e.printStackTrace();
							}
						}

						else {
							boolean T=false;
							if (matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] ==2)
							{
								T=true;
							}
							matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] = 2;
							int index2 = 0;  // Index into 1D array
							for (int i = 0; i < rows; i++) {
								for (int j = 0; j < columns; j++) {
									for (int k = 0; k < depth; k++) {
										matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
										index2++;
									}
								}
							}
							if (T==false) {
								parkingLot.setEmptySpots(parkingLot.getEmptySpots()-1);
								parkingLot.setNumberOfInactiveParkings(parkingLot.getNumberOfInactiveParkings() + 1);
								/****/
								inactivenumber halo=new inactivenumber(parkingLot.getId());
								session.save(halo);
								/****/
							}
							parkingLot.setMatrix(matrix1d);
						//	parkingLot.setEmptySpots(parkingLot.getEmptySpots()+1);
							session.update(parkingLot);
							try {
								client.sendToClient(new Message("#InactiveSuccess"));
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#active")){
			System.out.println("activvavvaa");
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<ParkingLot> parkingLotList = getAll(ParkingLot.class);
			Message msg1 = ((Message) msg);
			int parkingLotId = (int) msg1.getObject1();
			int parkingLotRow = (int) msg1.getObject2();
			int parkingLotCol = (int) msg1.getObject3();
			int parkingLotDepth = (int) msg1.getObject4();
			System.out.println(parkingLotCol);
			System.out.println(parkingLotId);
			for (ParkingLot parkingLot : parkingLotList) {
				if (parkingLot.getId() == parkingLotId) {
					if (parkingLot.getNumberOfColumns()-1 < parkingLotCol || 0 > parkingLotCol){
						try {
							client.sendToClient(new Message("#NumberOfColIsWrong"));
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
					else {
						int rows = parkingLot.getNumberOfRows();
						int columns = parkingLot.getNumberOfColumns();
						int depth = parkingLot.getDepth();
						byte[] matrix1d = parkingLot.getMatrix();
						int[][][] matrix3d = new int[rows][columns][depth];
						int index = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
									index++;
								}
							}
						}
						boolean T=false;
						if(matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] == 2){
							T=true;
							matrix3d[parkingLotRow][parkingLotCol][parkingLotDepth] = 0;
						}
						int index2 = 0;  // Index into 1D array
						for (int i = 0; i < rows; i++) {
							for (int j = 0; j < columns; j++) {
								for (int k = 0; k < depth; k++) {
									matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
									index2++;
								}
							}
						}
						parkingLot.setMatrix(matrix1d);
						if (T==true) {
							parkingLot.setEmptySpots(parkingLot.getEmptySpots()+1);
							parkingLot.setNumberOfInactiveParkings(parkingLot.getNumberOfInactiveParkings() - 1);
						}
						session.update(parkingLot);
						try {
							client.sendToClient(new Message("#ActiveSuccess"));
						}
						catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			session.getTransaction().commit();
			session.close();
		}

		if(msgString.equals("#renewsub")){
			System.out.println("subsubren");
			session = sessionFactory.openSession();
			session.beginTransaction();

			List<FullSubscriber> fullSubscriberList = getAll(FullSubscriber.class);
			List<RegularSubscriber> regularSubscriberList = getAll(RegularSubscriber.class);
			Message msg1 = ((Message) msg);
			int sub_id = ((int) msg1.getObject1());
			System.out.println(sub_id);
			for (FullSubscriber fullSubscriber : fullSubscriberList) {
				if (fullSubscriber.getSubscriberId() == sub_id) {
					fullSubscriber.setSubscriptionExpiryDate(fullSubscriber.getSubscriptionExpiryDate().plus(Duration.ofDays(28)));
					session.update(fullSubscriber);
				}
			}
			for (RegularSubscriber regularSubscriber : regularSubscriberList) {
				if (regularSubscriber.getSubscriberId() == sub_id) {
					regularSubscriber.setSubscriptionExpiryDate(regularSubscriber.getSubscriptionExpiryDate().plus(Duration.ofDays(28)));
					session.update(regularSubscriber);
				}
			}
			try {
				client.sendToClient(new Message("#subscriptionrenewed"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

		if (msgString.equals("#newpassadmin")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("newpassadmin");
			List<Admin> listadmin = getAll(Admin.class);
			Message msg1 = ((Message) msg);
			for (Admin p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (tmp.equals(msg1.getObject1().toString())) {
					System.out.format(msg1.getObject1().toString());
					p.setPassword(encrypt((String) msg1.getObject2(), "1234567812345678"));
					message.setObject1("yes");
					session.update(p);
					session.flush();
					session.getTransaction().commit();
				}
			}
			/*try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
			session.getTransaction().commit();
			session.close();
		}
		/*if (msgString.equals("#CancelReservation")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("CancelReservation");
			System.out.println("aa");
			Message cpymsg = ((Message) msg);

			List<Order> ListOrders = getAll(Order.class);
			List<RegularSubscriber> ListRegularSubscriber = getAll(RegularSubscriber.class);
			List<FullSubscriber> ListFullSubscriber = getAll(FullSubscriber.class);
			List<ParkingLot> ListParkingLot = getAll(ParkingLot.class);
			List<Car> ListCar = getAll(Car.class);

			String PersonIdString = cpymsg.getObject1().toString();
			int PersonId=Integer.parseInt(PersonIdString);

			String Password = cpymsg.getObject2().toString();

			String NumberOfOrderString = cpymsg.getObject3().toString();
			int NumberOfOrder=Integer.parseInt(NumberOfOrderString);

			int FlagCancelOrder=0;

			int TotalHours=0;

			int Refund=0;

			int CarNumber=0;

			//Authenicate
			for(Order order : ListOrders)
			{
				if(order.getOrderId()==NumberOfOrder)
				{
					if(order.getPersonId()==PersonId && order.getPassword().equals(Password))
						FlagCancelOrder=1;
				}
			}

			if(FlagCancelOrder==0)
				message.setObject1("One Of The Details is Not Right");

			if(FlagCancelOrder==1)
			{
				message.setObject1("Your Order Has Been Canceled");
				for(Order order : ListOrders)
				{
					if (order.getOrderId()==NumberOfOrder)
					{
						for(Car car : ListCar)
						{
							if (car.getCarNumber()==order.getCarNumber())
								session.delete(car);
						}

						TotalHours=HoursBetweenDates(order.getEnterHour(),order.getEnterDay(),order.getEnterMonth(),order.getEnterYear(),order.getExitHour(),order.getExitDay(),order.getExitMonth(),order.getExitYear());

						if(order.getTypeOfOrder().equals("GuestPreOrder"))
						{
							System.out.println("canceorderguestpreorder");
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(1); // guest pre order
							String arr[] = price12.split(" ");
							int price2 = Integer.parseInt(arr[0]);

							//***************************************

							Refund=TotalHours*price2;
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("GuestOnSiteOrder"))
						{
							//***************************************

							int i = order.getParkingLotId() - 1;
							List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

							List<String> Prices = PricesList.get(i).getValueNote();
							String price12 = Prices.get(0); // guest on site
							String arr[] = price12.split(" ");
							int price1 = Integer.parseInt(arr[0]);

							//***************************************

							Refund=TotalHours*price1;
							message.setObject2("The Refund in Money is: ");
							message.setObject3(Refund);
						}

						if(order.getTypeOfOrder().equals("RegularSubscriberOrder"))
						{
							for(RegularSubscriber Subscriber : ListRegularSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}

						if(order.getTypeOfOrder().equals("FullSubscriberOrder"))
						{
							for(FullSubscriber Subscriber : ListFullSubscriber)
							{
								if(Subscriber.getId()==PersonId)
								{
									Subscriber.setRemainingHours(Subscriber.getRemainingHours()+TotalHours);
									session.update(Subscriber);
								}
							}
							message.setObject2("The Refund in Hours is: ");
							message.setObject3(TotalHours);
						}
						for (ParkingLot parkingLot:ListParkingLot)
						{
							if(parkingLot.getId()==order.getParkingLotId())
							{
								parkingLot.setNumberOfOrders(parkingLot.getNumberOfOrders()-1);
								session.update(parkingLot);
							}
						}
						session.delete(order);
					}
				}
			}

			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			}
			catch (IOException e) {
				e.printStackTrace();
			}


		}*/

//		*************************************************************************************
//		3
//		*************************************************************************************

		/*if (msgString.equals("#Reservation")) {

			session = sessionFactory.openSession();
			session.beginTransaction();

			Message message = new Message("Reservation");
			System.out.println("bb");
			Message cpymsg = ((Message) msg);

//***************************************

			int i = Integer.parseInt(cpymsg.getObject9().toString()) - 1; // parking lot id object 1
			List<Prices> PricesList = getAll(Prices.class); // my prices is a list that includes all prices of all parking lots

			List<String> Prices = PricesList.get(i).getValueNote();
			String price12 = Prices.get(1); // guest pre order
			String arr[] = price12.split(" ");
			int price2 = Integer.parseInt(arr[0]);

//***************************************

			String TypeOfOrder = "GuestPreOrder";

			List<ParkingLot> ListParkingLots = getAll(ParkingLot.class);
			List<Order> ListOrders = getAll(Order.class);

			String EnterHourString = cpymsg.getObject1().toString();
			int EnterHour = Integer.parseInt(EnterHourString);

			String EnterDayString = cpymsg.getObject2().toString();
			int EnterDay = Integer.parseInt(EnterDayString);

			String EnterMonthString = cpymsg.getObject3().toString();
			int EnterMonth = Integer.parseInt(EnterMonthString);

			String EnterYearString = cpymsg.getObject4().toString();
			int EnterYear = Integer.parseInt(EnterYearString);

			String ExitHourString = cpymsg.getObject5().toString();
			int ExitHour = Integer.parseInt(ExitHourString);

			String ExitDayString = cpymsg.getObject6().toString();
			int ExitDay = Integer.parseInt(ExitDayString);

			String ExitMonthString = cpymsg.getObject7().toString();
			int ExitMonth = Integer.parseInt(ExitMonthString);

			String ExitYearString = cpymsg.getObject8().toString();
			int ExitYear = Integer.parseInt(ExitYearString);

			String ParkingLotIdString = cpymsg.getObject9().toString();
			int ParkingLotId = Integer.parseInt(ParkingLotIdString);

			String IDString = cpymsg.getObject10().toString();
			int ID = Integer.parseInt(IDString);

			String Password = cpymsg.getObject11().toString();

			boolean OnSite = (boolean) cpymsg.getObject12();

			String CarNumberString = cpymsg.getObject13().toString();
			int CarNumber = Integer.parseInt(CarNumberString);

			String Email = cpymsg.getObject14().toString();


			int TotalHours = HoursBetweenDates(EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear);

			int NumberOfOrdersInTheSameHours = 0;
			int MaxOrderId = 0;
			int Payment = 0;
			int FlagOrder = 1;

			ParkingLot park1 = null;

			for (ParkingLot ParkingLot : ListParkingLots) {
				//Check which ParkingLot in ListParkingLot has the same Id
				if (ParkingLot.getId() == ParkingLotId) {
					park1 = ParkingLot;
					for (Order Order : ListOrders) {
						//go through all orders in same Parking Lot
						if (Order.getParkingLotId() == ParkingLotId) {
							//check how many orders between the two dates
							if (Order.getEnterYear() <= ExitYear && Order.getExitYear() >= EnterYear) {
								if (Order.getEnterMonth() <= ExitMonth && Order.getExitMonth() >= EnterMonth) {
									if (Order.getEnterDay() <= ExitDay && Order.getExitDay() >= EnterDay) {
										if (Order.getEnterHour() <= ExitHour && Order.getExitHour() >= EnterHour) {
											//sum all orders in same parking lot in same hours
											NumberOfOrdersInTheSameHours++;
										}
									}
								}
							}
						}
					}

					//check if there is empty space
					if (ParkingLot.getCapacity() - ParkingLot.getNumberOfInactiveParkings() - NumberOfOrdersInTheSameHours > 0) {
						Payment = TotalHours * price2;

						for (Order Order : ListOrders) {
							//go through all orders in same Parking Lot
							if (Order.getOrderId() > MaxOrderId) {
								MaxOrderId = Order.getOrderId();
							}
						}
						MaxOrderId++;
						FlagOrder = 1;
					} else {
						FlagOrder = 0;
					}
				}
			}

			if (FlagOrder == 0) {
				message.setObject1("The Parking Lot is Full, Please Choose Another Parking Lot");
				System.out.println("The Parking Lot is Full, Please Choose Another Parking Lot");
			}

			if (FlagOrder == 1) {
				Order NewOrder = new Order(MaxOrderId, TypeOfOrder, EnterHour, EnterDay, EnterMonth, EnterYear, ExitHour, ExitDay, ExitMonth, ExitYear, ParkingLotId, ID, Password);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, EnterYear);
				cal.set(Calendar.MONTH, EnterMonth-1);
				cal.set(Calendar.DAY_OF_MONTH, EnterDay);
				cal.set(Calendar.HOUR_OF_DAY, EnterHour);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);

				Date date = cal.getTime();

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dateString = formatter.format(date);
				NewOrder.setEntryTime(dateString);
				///////////////////////////////
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, ExitYear);
				cal1.set(Calendar.MONTH, ExitMonth-1);
				cal1.set(Calendar.DAY_OF_MONTH, ExitDay);
				cal1.set(Calendar.HOUR_OF_DAY, ExitHour);
				cal1.set(Calendar.MINUTE, 0);
				cal1.set(Calendar.SECOND, 0);

				Date date1 = cal1.getTime();
				;
				String dateString1 = formatter.format(date1);
				NewOrder.setExitTime(dateString1);
				NewOrder.setSubId(-1);

				NewOrder.setCarNumber(CarNumber);
				NewOrder.setEmail(Email);

				Car NewCar = new Car(CarNumber);
				session.save(NewCar);

//				NewOrder.setParkinglot(park1);

				if (OnSite == true)
					NewOrder.setAlreadyInParkingLot(true);
				else
					NewOrder.setAlreadyInParkingLot(false);

				session.save(NewOrder);

				message.setObject1("Your Order Confirmed");
				message.setObject2(MaxOrderId);
				message.setObject3(Payment);

				for (ParkingLot ParkingLot : ListParkingLots) {
					if (ParkingLot.getId() == ParkingLotId) {
						ParkingLot.setNumberOfOrders(ParkingLot.getNumberOfOrders() + 1);
						session.update(ParkingLot);
					}
				}
			}
			session.getTransaction().commit();
			session.close();
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
		//		*************************************************************************************
//		*************************************************************************************
		if (msgString.equals("#newsubscribe")) {
			session = sessionFactory.openSession();
			int flag=1;
			session.beginTransaction();
			Message msg1 = ((Message) msg);
			System.out.format(msg1.getObject2().toString());
			List<FullSubscriber> listadmin = getAll(FullSubscriber.class);
			List<RegularSubscriber> listadmin1 = getAll(RegularSubscriber.class);
			for (FullSubscriber p:listadmin)
			{
				String tmp="";tmp+=p.getId();
				if(tmp.equals(msg1.getObject1().toString()))
				{
					flag=0;
				}
				if((p.getEmail().equals(msg1.getObject4().toString())))
				{
					flag=-1;
				}
			}
			for (RegularSubscriber p:listadmin1)
			{
				String tmp="";tmp+=p.getId();
				if(tmp.equals(msg1.getObject1().toString()))
				{
					flag=0;
				}
				if((p.getEmail().equals(msg1.getObject4().toString())))
				{
					flag=-1;
				}
			}
			if ((msg1.getObject7().toString().equals("full"))&&flag==1) {
				FullSubscriber x = new FullSubscriber((Integer.parseInt(msg1.getObject1().toString())), msg1.getObject2().toString(),
						msg1.getObject3().toString(), msg1.getObject4().toString(), encrypt((String) msg1.getObject5(), "1234567812345678"),
						msg1.getObject6().toString(),"0");

				session.save(x);
				session.update(x);
			} if ((msg1.getObject7().toString().equals("regular"))&&flag==1){
				RegularSubscriber y = new RegularSubscriber((Integer.parseInt(msg1.getObject1().toString())), msg1.getObject2().toString(),
						msg1.getObject3().toString(), msg1.getObject4().toString(), encrypt((String) msg1.getObject5(), "1234567812345678"),
						msg1.getObject6().toString(),"0");
				session.save(y);
				session.update(y);
			}
			if (flag==0)
			{
				client.sendToClient(new Message("id is used before","id is used before"));
			}
			if (flag==-1)
			{
				client.sendToClient(new Message("email is used before","email is used before"));
			}
			if (flag==1)
			{
				System.out.println(msg1.getObject7().toString());
				client.sendToClient(new Message("yes","yes",msg1.getObject7().toString()));
			}
			session.getTransaction().commit();
			session.close();
		}

		/*if (msgString.startsWith("newCompliain")) {

			String[] a = msgString.split("\\^");
			Complaint c = new Complaint(a[1], Integer.parseInt(a[2]), 1);
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(c);
			client.sendToClient(new Message("add compliant succ", c.getId()));
			session.getTransaction().commit();
			session.close();

		}*/
		if (msgString.startsWith("newCompliain")) {
			Message msg2=((Message) msg);
			session = sessionFactory.openSession();
			session.beginTransaction();
			int flag=1;
			System.out.println("here");
			if(Integer.parseInt(msg2.getObject2().toString())==1){
				System.out.println("here1");
				List<Order> orders=getAll(Order.class);
				System.out.println("here2");
				for(Order o1:orders)
				{
					System.out.println(flag);
					if(o1.getPassword()!=null && o1.getPassword().equals(msg2.getObject3().toString())){
						flag=0;
						System.out.println("find password");
					}
				}
				if(flag==1)
				{
					System.out.println("pass not found");
					flag=0;
					client.sendToClient(new Message("add compliant succ",-1));
				}
				else
					flag=1;
			}
			if (flag==1) {
				String[] a = msgString.split("\\^");
				Complaint c = new Complaint(a[1], Integer.parseInt(msg2.getObject1().toString()), Integer.parseInt(a[2]));

				session.flush();
				session.save(c);

				client.sendToClient(new Message("add compliant succ", c.getId()));

			}
			session.getTransaction().commit();
			session.close();

		}
		if (((Message) msg).getMessage().startsWith("Gussetbring")) {

			session = sessionFactory.openSession();
			if (!session.isConnected() || session == null)
				session.beginTransaction();
			String[] a = msgString.split("\\^");
			List<Complaint> c = getAll(Complaint.class);
			List<Complaint> complaints = new ArrayList<Complaint>();

			for (Complaint e : c) {
				if (e.getId() == Integer.parseInt(a[1])) {
					complaints.add(e);
					System.out.println(e);
				}
			}
			try {
				client.sendToClient(new Message("complaints", complaints));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}
		if (((Message) msg).getMessage().startsWith("bring")) {

			session = sessionFactory.openSession();
			if (!session.isConnected() || session == null)
				session.beginTransaction();
			String[] a = msgString.split("\\^");
			List<Complaint> c = getAll(Complaint.class);
			List<Complaint> complaints = new ArrayList<Complaint>();

			for (Complaint e : c) {
				if (e.getUserId() == Integer.parseInt(a[1])) {
					complaints.add(e);
					System.out.println(e);
				}
			}
			try {
				client.sendToClient(new Message("complaints", complaints));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}

	/*	if (((Message) msg).getMessage().startsWith("bring")) {

			session = sessionFactory.openSession();
			session.beginTransaction();
			String[] a = msgString.split("\\^");
			List<Complaint> c = getAll(Complaint.class);
			List<Complaint> complaints = new ArrayList<Complaint>();

			for (Complaint e : c) {
				if (e.getParkingLotId() == Integer.parseInt(a[1])) {
					complaints.add(e);
					System.out.println(e);
				}
			}
			try {
				client.sendToClient(new Message("complaints", complaints));
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.getTransaction().commit();
			session.close();
		}*/

		if (((Message) msg).getMessage().startsWith("#response")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message m = (Message) msg;
			List<Complaint> c = getAll(Complaint.class);
			for (Complaint e : c) {
				if (e.getId() == Integer.parseInt(m.getObject1().toString())) {
					e.setStatus("answered");
					e.setResponse(m.getObject2().toString());
					session.update(e);

				}
			}
			session.flush();
			session.getTransaction().commit();
			session.close();
		}

		if (((Message) msg).getMessage().startsWith("#bringall")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Complaint> c = getAll(Complaint.class);
			client.sendToClient(new Message("allComplaints", c));
			session.getTransaction().commit();
			session.close();
		}

		if (((Message) msg).getMessage().startsWith("#2bringall")) {
			System.out.println("in server refresh table1");
			session = sessionFactory.openSession();
			System.out.println("in server refresh table2");
			if (!session.isConnected() || session == null)
				session.beginTransaction();
			System.out.println("in server refresh table");
			List<Complaint> c = getAll(Complaint.class);
			client.sendToClient(new Message("2allComplaints", c));
			session.getTransaction().commit();
			session.close();
		}
	}
}