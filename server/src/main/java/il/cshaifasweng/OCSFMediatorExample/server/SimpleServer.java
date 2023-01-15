package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

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
		configuration.addAnnotatedClass(Car.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(OneTimeCustomer.class);
		configuration.addAnnotatedClass(OnSiteCustomer.class);
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(ParkingLot.class);
		configuration.addAnnotatedClass(RegularSubscriber.class);
		configuration.addAnnotatedClass(ParkingLotManager.class);
		configuration.addAnnotatedClass(ParkingLotEmployee.class);
		configuration.addAnnotatedClass(FullSubscriber.class);
		configuration.addAnnotatedClass(Prices.class);
		configuration.addAnnotatedClass(review.class);

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

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) throws Exception {
		String msgString = msg.toString();

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
		}

		if (msgString.equals("#loginadmin")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("loginadmin");
			List<Admin> listadmin = getAll(Admin.class);
			List<ParkingLotManager> listadmin2 = getAll(ParkingLotManager.class);
			Message msg1 = ((Message) msg);
			int c = 0;
			int c2 = 0;
			int twoconnectedclients=0;
			for (Admin p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (p.getIsConnected().equals("1")&&p.getOccupation().equals("Chain Manager") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
				}
				if (p.getIsConnected().equals("0")&&p.getOccupation().equals("Chain Manager") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					twoconnectedclients=1;
					message.setObject1("yes Chain Manager");
				}
				if (p.getIsConnected().equals("1")&&p.getOccupation().equals("Customer Service") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					twoconnectedclients=-1;
				}
				if (p.getIsConnected().equals("0")&&p.getOccupation().equals("Customer Service") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					twoconnectedclients=1;
					p.setIsConnected("1");
					session.save(p);
					session.update(p);
					message.setObject1("yes Customer Service");
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
			}
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
		}


		if (msgString.equals("#logoutchain")) {
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
		if (msgString.equals("#addcar")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			List<Car> carList = getAll(Car.class);
			List<FullSubscriber> fullSubscriberList = getAll(FullSubscriber.class);
			List<RegularSubscriber> regularSubscriberList = getAll(RegularSubscriber.class);
			Message msg1 = ((Message) msg);
			int carNumber = (int) msg1.getObject1();
			int sub_id = (int) msg1.getObject2();
			String typeOfSub = (String) msg1.getObject3();
			for (Car car : carList) {
				if (car.getCarNumber() == carNumber) {
					client.sendToClient(new Message("#caralreadylinked"));
				}
			}
			Car car = new Car(carNumber);
			if (typeOfSub.equals("fullSub")) {
				for (FullSubscriber fullSubscriber : fullSubscriberList) {
					if (fullSubscriber.getId() == sub_id) {
						fullSubscriber.addCar(car);
						session.update(fullSubscriber);
					}
				}
			} else {
				for (RegularSubscriber regularSubscriber : regularSubscriberList) {
					if (regularSubscriber.getId() == sub_id) {
						regularSubscriber.addCar(car);
						session.update(regularSubscriber);
					}
				}
			}
			session.save(car);
			session.flush();
			session.getTransaction().commit();
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
			String typeOfSub = (String) msg1.getObject3();

			for (Car car : carList) {
				if (car.getCarNumber() == carNumber) {
					if (typeOfSub.equals("fullSub")) {
						for (FullSubscriber fullSubscriber : fullSubscriberList) {
							if (fullSubscriber.getId() == sub_id) {
								fullSubscriber.removeCar(car);
								session.update(fullSubscriber);
							}
						}
					} else {
						for (RegularSubscriber regularSubscriber : regularSubscriberList) {
							if (regularSubscriber.getId() == sub_id) {
								regularSubscriber.addCar(car);
								session.update(regularSubscriber);
							}
						}
					}
					session.delete(car);
				}
			}
			session.flush();
			session.getTransaction().commit();
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
		}

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
					client.sendToClient(new Message("yes","yes"));
				}
			}
		//}
		if (msgString.startsWith("newCompliain")) {

					String[] a = msgString.split("\\^");
					Complaint c = new Complaint(a[1], Integer.parseInt(a[2]));
					session = sessionFactory.openSession();
					session.beginTransaction();
					session.save(c);
					client.sendToClient(new Message("add compliant succ", c.getId()));

				}
		if (((Message) msg).getMessage().startsWith("bring")) {

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
				}
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
				}
		if (((Message) msg).getMessage().startsWith("#bringall")) {
					session = sessionFactory.openSession();
					session.beginTransaction();
					List<Complaint> c = getAll(Complaint.class);
					client.sendToClient(new Message("allComplaints", c));
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
		}
		if (msgString.equals("#loginguest")) {
			session=sessionFactory.openSession();
			session.beginTransaction();
			List<Order> orders=getAll(Order.class);
			int flag=0;
			for(Order o1:orders)
			{
				if(o1.getEmail().equals(((Message) msg).getObject1().toString())) {
					o1.setIsConnected(1);
					session.save(o1);
					client.sendToClient("loginguesttrue");
					flag=1;
				}
				if(flag==0){
					client.sendToClient("loginguestfail");
				}
				session.flush();
				session.getTransaction().commit();

			}

		}
		session.flush();
		session.getTransaction().commit();
	}
}