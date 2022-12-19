package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Parking;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Warning;
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
		configuration.addAnnotatedClass(Parking.class);
		configuration.addAnnotatedClass(Price.class);
		ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		return configuration.buildSessionFactory(serviceRegistry);
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
			Warning warning = new Warning("Warning from server!");
			try {
				client.sendToClient(warning);
				System.out.format("Sent warning to client %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (msgString.startsWith("#showparkfun")) {
			SessionFactory sessionFactory=getSessionFactory();
			session=sessionFactory.openSession();
			session.beginTransaction();
			List<Parking> parks=getAll(Parking.class);
			System.out.println("parking list");
			String tmp="";
			for (Parking p:parks){
				tmp+=String.format("Id: %d, Name: %s \n" ,p.getId(),p.getName());
			}
			if(session!=null) {
				session.close();
				session.getSessionFactory().close();
			}

			Warning warning = new Warning(tmp);
			try {
				client.sendToClient(warning);
				System.out.format("Parking Show %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
