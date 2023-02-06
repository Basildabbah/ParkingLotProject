package il.cshaifasweng.OCSFMediatorExample.server;

import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import il.cshaifasweng.OCSFMediatorExample.entities.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App
{
    static Session session;
    private static ScheduledExecutorService executor;

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
        configuration.addAnnotatedClass(ChainManager.class);

        configuration.addAnnotatedClass(review.class);

        configuration.addAnnotatedClass(FullSubscriber.class);
        configuration.addAnnotatedClass(Prices.class);

        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void initializeData() throws Exception {
        String secretKey = "1234567812345678";
        review l1=new review("messiiiiiii","very bad","1");
        session.save(l1);
        review l2=new review("shir","bad ","2");
        session.save(l2);
        review l3=new review("ronaldo","good","5");
        session.save(l3);
        review l4=new review("speed","nice","4");
        session.save(l4);
        review l5=new review("zohal","awesome","5");
        session.save(l5);
        ParkingLot parkingLot1 = new ParkingLot(3, 8, 3, new byte[4*8*4]);
        ParkingLot parkingLot2 = new ParkingLot(3, 4, 3, new byte[4*4*4]);
        ParkingLot parkingLot3 = new ParkingLot(3, 4, 3, new byte[4*4*4]);
        parkingLot1.setNumberOfInactiveParkings(71);


        byte[] matrix1d = parkingLot1.getMatrix();  // Get the one-dimensional matrix


        int[][][] matrix3d = new int[3][8][3];  // Create 3D matrix

        int index = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d[i][j][k] = matrix1d[index] & 0xff;  // Copy element from 1D array to 3D matrix
                    index++;
                    matrix3d[i][j][k] = 2;
                }
            }
        }

        matrix3d[0][0][0] = 2;
        matrix3d[2][1][0] = 0;

        int index2 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d[index2] = (byte) matrix3d[i][j][k];  // Copy element from 3D array to 1D array
                    index2++;
                }
            }
        }

        parkingLot1.setMatrix(matrix1d);
        //**************************************************************

        parkingLot1.setCapacity(parkingLot1.getNumberOfRows()*parkingLot1.getNumberOfColumns()*parkingLot1.getDepth());
        parkingLot2.setCapacity(parkingLot2.getNumberOfRows()*parkingLot2.getNumberOfColumns()*parkingLot2.getDepth());
        parkingLot3.setCapacity(parkingLot3.getNumberOfRows()*parkingLot3.getNumberOfColumns()*parkingLot3.getDepth());

        //**************************************************************

        List<String> typeOfParking11 = Arrays.asList("mezdament", "one time","client for one car","client for more car","full subscribtion");
        List<String> PAymentMethoud11 = Arrays.asList("paypal", "Credit card","visa");
        List<String> valueNote11 = Arrays.asList("8 Per Hour","7 Per Hour","60 hour","54 hour","72 hour");
        Prices price11 = new Prices(typeOfParking11, PAymentMethoud11, valueNote11 , parkingLot1);
        session.save(price11);
        List<String> typeOfParking22 = Arrays.asList("mezdament", "one time","client for one car","client for more car","full subscribtion");
        List<String> PAymentMethoud22 = Arrays.asList("paypal", "Credit card","visa");
        List<String> valueNote22 = Arrays.asList("6 Per Hour","6 Per Hour","60 hour","54 hour","72 hour");
        Prices price22= new Prices(typeOfParking22, PAymentMethoud22, valueNote22 , parkingLot2);
        session.save(price22);


        List<String> typeOfParking33 = Arrays.asList("mezdament", "one time","client for one car","client for more car","full subscribtion");
        List<String> PAymentMethoud33 = Arrays.asList("paypal", "Credit card","visa");
        List<String> valueNote33 = Arrays.asList("10 Per Hour","6 Per Hour","60 hour","54 hour","72 hour");
        Prices price33= new Prices(typeOfParking33, PAymentMethoud33, valueNote33 , parkingLot3);
        session.save(price33);


        List<String> typeOfParking44 = Arrays.asList("mezdament", "one time","client for one car","client for more car","full subscribtion");
        List<String> PAymentMethoud44 = Arrays.asList("paypal", "Credit card","visa");
        List<String> valueNote44 = Arrays.asList("7 Per Hour","9 Per Hour","60 hour","54 hour","72 hour");
        Prices price44= new Prices(typeOfParking33, PAymentMethoud33, valueNote33 , parkingLot3);
        session.save(price44);

/*
/*
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
        Prices price3 = new Prices(typeOfParking3, paymentMethod3, valueNote3 , parkingLot3);*/

        session.save(parkingLot1);
        session.save(parkingLot2);
        session.save(parkingLot3);

        /*session.save(price1);
        session.save(price2);
        session.save(price3);*/

        ParkingLotManager parkingLotManager1 = new ParkingLotManager("John", "Doe", "john.doe@company.com", "1000", parkingLot1,"0");
        ParkingLotManager parkingLotManager2 = new ParkingLotManager("ada", "ada", "ada.ada@company.com", "1001", parkingLot2,"0");
        ParkingLotManager parkingLotManager3 = new ParkingLotManager("bs", "dsf", "bs.dsf@company.com", "1002", parkingLot3,"0");
        ChainManager chainManager1 = new ChainManager("Jane" ,"Smith", "jane.smith@company.com" ,  "abcde" , "0");
        Admin customerService = new Admin("Bob", "Johnson", "bob.johnson@company.com", "100","0");
        String encryptedString_ = encrypt(chainManager1.getPassword() , secretKey);
        chainManager1.setPassword(encryptedString_);
        String encryptedString_1 = encrypt(customerService.getPassword() , secretKey);
        customerService.setPassword(encryptedString_1);

        ParkingLotEmployee parkingLotEmployee = new ParkingLotEmployee("Alice", "Williams", "alice.williams@company.com", "qwerty",  parkingLot2,"0");
        String encryptedString11 = encrypt(parkingLotEmployee.getPassword() , secretKey);
        parkingLotEmployee.setPassword(encryptedString11);


        String encryptedString1 = encrypt(parkingLotManager1.getPassword() , secretKey);
        parkingLotManager1.setPassword(encryptedString1);

        String encryptedString2 = encrypt(parkingLotManager2.getPassword() , secretKey);
        parkingLotManager2.setPassword(encryptedString2);

        String encryptedString3 = encrypt(parkingLotManager3.getPassword() , secretKey);
        parkingLotManager3.setPassword(encryptedString3);

        session.save(parkingLotManager1);
        session.save(parkingLotManager2);
        session.save(parkingLotManager3);
        session.save(chainManager1);
        session.save(customerService);
        session.save(parkingLotEmployee);

        FullSubscriber fullSubscriber = new FullSubscriber(149,"Jane", "Smith", "jane.smith@company.com", "2", "111","0");
        String x = encrypt(fullSubscriber.getPassword() , secretKey);
        fullSubscriber.setPassword(x);


        RegularSubscriber regularSubscriber = new RegularSubscriber(123,"John", "Smith", "jane.smith@company.com", "1", "333","0");
        x = encrypt(regularSubscriber.getPassword() , secretKey);
        regularSubscriber.setPassword(x);
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
        Complaint c1=new Complaint("aaaaaaa1",6,1);
        Complaint c2=new Complaint("bbbbb1",6,1);
        Complaint c3=new Complaint("aaa2",7,2);
        session.save(c1);
        session.save(c2);
        session.save(c3);
//        Order o1=new Order(car1,"m@gmail.com");
//        session.save(o1);
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
            server = new SimpleServer(7777);
            SessionFactory sessionFactory = getSessionFactory();
            session = sessionFactory.openSession();
            session.beginTransaction();

            initializeData();

            executor = Executors.newSingleThreadScheduledExecutor();
            executor.scheduleAtFixedRate(App::sendEmail, 0, 1, TimeUnit.SECONDS);

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
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(App::sendEmail, 0, 59, TimeUnit.MINUTES);

        server.listen();
    }

   /* private static void sendEmail() {
//        List<Order> x=getAll(Order.class);
//        LocalDateTime t=LocalDateTime.now();
////        System.out.println(t.getHour());
////        System.out.println(t.getMinute());
////        System.out.println(t.getDayOfMonth());
////        System.out.println( t.getMonthValue());
////        System.out.println(t.getDayOfYear());
//        for (Order xi:x) {
//            SendEmail.SendEmail(" ", " ", " ");
//        }
    }*/

    private static SessionFactory sessionFactory = getSessionFactory();
    private static void sendEmail() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        List<Order> x=getAll(Order.class);
        LocalDateTime t=LocalDateTime.now();
        for (Order xi:x) {
            if (xi.getSubId() == 0 && !xi.isAlreadyInParkingLot() && xi.getEnterHour() == t.getHour() && xi.getEnterYear() == t.getYear() && xi.getEnterDay() == t.getDayOfMonth() && xi.getEnterMonth() == t.getMonthValue())
                SendEmail.SendEmail(xi.getEmail(), "Customer didn't arrive! ", "hello, we would like to alert you that you have ordered a parking spot from hour:  " + xi.getEnterHour() + " to hour:  " + xi.getExitHour() + " ,the order will be cancelled if you didnt arrive in 30 minutes! Thank you ");
        }
       // session.close();
    }

}
