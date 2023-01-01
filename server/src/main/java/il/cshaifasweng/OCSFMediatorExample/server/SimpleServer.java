package il.cshaifasweng.OCSFMediatorExample.server;

import il.cshaifasweng.OCSFMediatorExample.server.ocsf.AbstractServer;
import il.cshaifasweng.OCSFMediatorExample.server.ocsf.ConnectionToClient;

import java.io.IOException;
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

public class SimpleServer extends AbstractServer {
	public SimpleServer(int port) {
		super(port);

	}
	private static Session session;
	private static SessionFactory getSessionFactory(){
		Configuration configuration=new Configuration();
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