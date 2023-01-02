package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SimpleServer extends AbstractServer {
	public SimpleServer(int port) {
		super(port);

	}
	private static Session session;
	private static SessionFactory getSessionFactory(){
		Configuration configuration=new Configuration();
		configuration.addAnnotatedClass(Admin.class);
		configuration.addAnnotatedClass(CanceledOrder.class);
		configuration.addAnnotatedClass(Car.class);
		configuration.addAnnotatedClass(Client.class);
		configuration.addAnnotatedClass(Complaint.class);
		configuration.addAnnotatedClass(OneTimeCustomer.class);
		configuration.addAnnotatedClass(OnSiteCustomer.class);
		configuration.addAnnotatedClass(Order.class);
		configuration.addAnnotatedClass(ParkingLot.class);
		configuration.addAnnotatedClass(RegularSubscriber.class);
		configuration.addAnnotatedClass(Subscriber.class);
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
	}

	private static void initializeData(){

	}
	public static <T> List<T> getAll(Class<T> object){
		CriteriaBuilder builder=session.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery=builder.createQuery(object);
		Root<T> rootEntry=criteriaQuery.from(object);
		CriteriaQuery<T> allCriteriaQuery=criteriaQuery.select(rootEntry);

		TypedQuery<T> allQuery =session.createQuery(allCriteriaQuery);
		return allQuery.getResultList();
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		String msgString = msg.toString();
		if (msgString.startsWith("#warning")) {
			Message message = new Message("Warning from server!");
			try {
				client.sendToClient(message);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}