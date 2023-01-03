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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
/**
 * Hello world!
 *
 */
public class App 
{
    static Session session;

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

    public static Session getSession() {
        return session;
    }

    private static SessionFactory getSessionFactory(){
        Configuration configuration=new Configuration();
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
        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void initializeData() throws Exception {
        String secretKey = "1234567812345678";

        ParkingLot parkingLot1 = new ParkingLot(5, 10, 2, new byte[5*10*2]);
        ParkingLot parkingLot2 = new ParkingLot(6, 12, 3, new byte[6*12*3]);
        ParkingLot parkingLot3 = new ParkingLot(7, 14, 4, new byte[7*14*4]);


        List<String> typeOfParking1 = Arrays.asList("Short-term parking", "Long-term parking");
        List<String> paymentMethod1 = Arrays.asList("Cash", "Credit card");
        List<String> valueNote1 = Arrays.asList("10 EUR", "50 EUR");
        Prices price1 = new Prices(typeOfParking1, paymentMethod1, valueNote1 , parkingLot1);

        List<String> typeOfParking2 = Collections.singletonList("Valet parking");
        List<String> paymentMethod2 = Collections.singletonList("PayPal");
        List<String> valueNote2 = Collections.singletonList("100 EUR");
        Prices price2 = new Prices(typeOfParking2, paymentMethod2, valueNote2 , parkingLot2);

        List<String> typeOfParking3 = Arrays.asList("Self-service parking", "Valet parking");
        List<String> paymentMethod3 = Arrays.asList("Cash", "Credit card", "PayPal");
        List<String> valueNote3 = Arrays.asList("5 EUR", "25 EUR", "75 EUR");
        Prices price3 = new Prices(typeOfParking3, paymentMethod3, valueNote3 , parkingLot3);

        session.save(parkingLot1);
        session.save(parkingLot2);
        session.save(parkingLot3);

        session.save(price1);
        session.save(price2);
        session.save(price3);

        ParkingLotManager parkingLotManager1 = new ParkingLotManager("John", "Doe", "john.doe@company.com", "12345", parkingLot1);
        ParkingLotManager parkingLotManager2 = new ParkingLotManager("ada", "ada", "ada.ada@company.com", "15794", parkingLot2);
        ParkingLotManager parkingLotManager3 = new ParkingLotManager("bs", "dsf", "bs.dsf@company.com", "14973", parkingLot3);
        Admin chainManager = new Admin("Jane", "Smith", "jane.smith@company.com", "abcde", "Chain Manager");
        Admin customerService = new Admin("Bob", "Johnson", "bob.johnson@company.com", "password", "Customer Service");
        ParkingLotEmployee parkingLotEmployee = new ParkingLotEmployee("Alice", "Williams", "alice.williams@company.com", "qwerty",  parkingLot2);

        String encryptedString1 = encrypt(parkingLotManager1.getPassword() , secretKey);
        parkingLotManager1.setPassword(encryptedString1);

        String encryptedString2 = encrypt(parkingLotManager2.getPassword() , secretKey);
        parkingLotManager2.setPassword(encryptedString2);

        String encryptedString3 = encrypt(parkingLotManager3.getPassword() , secretKey);
        parkingLotManager3.setPassword(encryptedString3);

        session.save(parkingLotManager1);
        session.save(parkingLotManager2);
        session.save(parkingLotManager3);
        session.save(chainManager);
        session.save(customerService);
        session.save(parkingLotEmployee);

        FullSubscriber fullSubscriber = new FullSubscriber(149,"Jane", "Smith", "jane.smith@company.com", "abcde", "111");
        RegularSubscriber regularSubscriber = new RegularSubscriber(123,"Jane", "Smith", "jane.smith@company.com", "abcde", "333");
        Car car1 = new Car(2132456);
        Car car2 = new Car(4525242);

        session.save(car1);
        session.save(car2);


        fullSubscriber.addCar(car1);
        regularSubscriber.addCar(car2);

        session.save(fullSubscriber);
        session.save(regularSubscriber);

        OneTimeCustomer oneTimeCustomer = new OneTimeCustomer(149,"Jane", "Smith", "jane.smith@company.com", "789" , car1);
        OnSiteCustomer onSiteCustomer = new OnSiteCustomer(149,"Jane", "Smith", "jane.smith@company.com", "789" , car2);
        session.save(oneTimeCustomer);
        session.save(onSiteCustomer);

        session.getTransaction().commit();



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

            List<ParkingLotManager> parkingLotManagers = getAll(ParkingLotManager.class);
            String secretKey = "1234567812345678";
            for (ParkingLotManager parkingLotManager : parkingLotManagers){
                System.out.println("Decrypted string: " + decrypt(parkingLotManager.getPassword(), secretKey));
            }
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
