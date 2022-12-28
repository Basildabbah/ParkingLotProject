package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.entities.Parking;
import il.cshaifasweng.OCSFMediatorExample.entities.Price;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static il.cshaifasweng.OCSFMediatorExample.server.App.getSession;

public class SimpleServer extends AbstractServer {
	static int x=0;
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

	private static void initializeData(){
		Parking a1=new Parking("aaaa",3);
		Parking a2=new Parking("bbbb",5);
		Parking a3=new Parking("cccc",8);
		session.save(a1);
		session.save(a2);
		session.save(a3);
		session.flush();
		Price p1=new Price("mezdament","hours",8);
		Price p2=new Price("one time","hours",8);
		Price p3=new Price("client for one car","fixed for 60 hours",8);
		Price p4=new Price("client for more car","fixed for 54 hours* cars",8);
		Price p5=new Price("full subscribtion","fixed for 72 hours",8);

		session.save(p1);
		session.save(p2);
		session.save(p3);
		session.save(p4);
		session.save(p5);
		session.flush();
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
		if(x==0) {
			SessionFactory sessionFactory = getSessionFactory();
			session = sessionFactory.openSession();
			session.beginTransaction();
			initializeData();
			x++;
		}

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



		if (msgString.startsWith("#showpricefun")) {
			List<Price> parks=getAll(Price.class);
			Message message = new Message("showpricefun");
			message.setList(parks);
			try {
				client.sendToClient(message);
				System.out.format("Price Show %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}



		if (msgString.startsWith("#showparkfun")) {
			List<Parking> parks=getAll(Parking.class);
			System.out.println("parking list");
			Message message = new Message("showparkfun");
			message.setList(parks);
			try {
				client.sendToClient(message);
				System.out.format("Parking Show %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		if (msgString.startsWith("#okey")) {
			List<Price> parks=getAll(Price.class);
			String tmp="";
			String tmp2="";
			String x="";
			int i=0;
			for (i=5;i<msgString.length();i++)
			{
				if(msgString.charAt(i)!=',')
				{
					tmp+=msgString.charAt(i);
				}
				else
				{
					break;
				}
			}
			for (i=i+1;i<msgString.length();i++)
			{
				tmp2+=msgString.charAt(i);
			}
			i=0;

			System.out.println(tmp);
			System.out.println(tmp2);

			for (Price p:parks){


				x="";
				x+=parks.get(i).getId();
				if ((x.equals(tmp)))
				{
					System.out.println(p.getPrice());
					p.setPrice(Integer.parseInt(tmp2));
					session.update(p);
					session.getTransaction().commit();
					System.out.println(p.getPrice());

				}
				i++;
			}
			System.out.println("okokokok");
			Message message = new Message("showpricefun");
			message.setList(parks);
			try {
				client.sendToClient(message);
				System.out.format("Price Show %s\n", client.getInetAddress().getHostAddress());
			} catch (IOException e) {
				e.printStackTrace();
			}		}
	}

}


//	String hostname = "localhost";
//			int port = 3306;
//			String database = "software";
//			String username = "root";
//			String password = "0547952152AZaz";
//
//			// Build the connection URL
//			String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database;
//			String tmp="";
//			ResultSet rs=null;
//			// Establish the connection
//			try (Connection conn = DriverManager.getConnection(url, username, password)) {
//				Statement stmt = conn.createStatement();
//
//				// Execute a SELECT query
//				rs = stmt.executeQuery("SELECT * FROM Parking");
//
//				// Process the results
////				while (rs.next()) {
////					// Get the column values
////					int id = rs.getInt("id");
////					String name = rs.getString("name");
////					int size = rs.getInt("size");
////
////					String x=String.format("ID: %d  size: %d \n",id,size );
////					tmp=tmp+x;
////				}
//
//			} catch (SQLException e) {
//				System.out.println("Unable to get data from table: " + e.getMessage());
//			}