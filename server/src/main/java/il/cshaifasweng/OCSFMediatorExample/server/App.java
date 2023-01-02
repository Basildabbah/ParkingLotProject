package il.cshaifasweng.OCSFMediatorExample.server;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    static Session session;

    public static Session getSession() {
        return session;
    }

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

        Admin parkingLotManager = new ParkingLotManager("John", "Doe", "john.doe@company.com", "12345", "Parking Lot Manager", 1);
        Admin chainManager = new Admin("Jane", "Smith", "jane.smith@company.com", "abcde", "Chain Manager");
        Admin customerService = new Admin("Bob", "Johnson", "bob.johnson@company.com", "password", "Customer Service");
        Admin parkingLotEmployee = new ParkingLotEmployee("Alice", "Williams", "alice.williams@company.com", "qwerty", "Parking Lot Employee", 2);

        session.save(parkingLotManager);
        session.save(chainManager);
        session.save(customerService);
        session.save(parkingLotEmployee);

        ParkingLot parkingLot1 = new ParkingLot(5, 10, 2, new byte[5*10*2]);
        ParkingLot parkingLot2 = new ParkingLot(6, 12, 3, new byte[6*12*3]);
        ParkingLot parkingLot3 = new ParkingLot(7, 14, 4, new byte[7*14*4]);


        session.save(parkingLot1);
        session.save(parkingLot2);
        session.save(parkingLot3);


    }
    public static <T> List<T> getAll(Class<T> object){
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery=builder.createQuery(object);
        Root<T> rootEntry=criteriaQuery.from(object);
        CriteriaQuery<T> allCriteriaQuery=criteriaQuery.select(rootEntry);

        TypedQuery<T> allQuery =session.createQuery(allCriteriaQuery);
        return allQuery.getResultList();
    }

	private static SimpleServer server;
    public static void main( String[] args ) throws IOException {
        try {
            server = new SimpleServer(3000);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            initializeData();

        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                session.getSessionFactory().close();
            }
        }
        server.listen();
    }
}
