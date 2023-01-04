package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.security.Key;
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

		if (msgString.equals("#loginadmin")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("loginadmin");
			List<Admin> listadmin = getAll(Admin.class);
			List<ParkingLotManager> listadmin2 = getAll(ParkingLotManager.class);
			Message msg1 = ((Message) msg);
			int c = 0;
			int c2 = 0;
			for (Admin p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (p.getOccupation().equals("Chain Manager") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					message.setObject1("yes Chain Manager");
				}
				if (p.getOccupation().equals("Customer Service") && tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					message.setObject1("yes Customer Service");
				}
			}
			for (ParkingLotManager p : listadmin2) {
				String tmp = "";
				tmp += p.getId();
				if ( tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					message.setObject1("yes parkinglotmanagers");
				}
			}



			if (c == 0) message.setObject1("no");
			try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
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
			for (FullSubscriber p : listadmin) {
				String tmp = "";
				tmp += p.getId();
				if (tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					message.setObject1("yes full_subscriber");
				}
			}
			for (RegularSubscriber p : listadmin2) {
				String tmp = "";
				tmp += p.getId();
				if (tmp.equals(msg1.getObject1()) && decrypt(p.getPassword(), "1234567812345678").equals(msg1.getObject2())) {
					c = 1;
					message.setObject1("yes regular_subscriber");
				}
			}
			if (c == 0) message.setObject1("no");
			try {
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
				if (tmp.equals(msg1.getObject1().toString()))
				{
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


		if (msgString.equals("#addcar_full_subscriber")) {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Message message = new Message("addcar_full_subscriber");
			List<Car> listadmin = getAll(Car.class);
			Message msg1 = ((Message) msg);
			System.out.format(msg1.getObject2().toString());
			/*try {
				client.sendToClient(message);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
		}
	}
}
