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
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        configuration.addAnnotatedClass(totalordertest.class);

        configuration.addAnnotatedClass(review.class);

        configuration.addAnnotatedClass(FullSubscriber.class);
        configuration.addAnnotatedClass(Prices.class);

        ServiceRegistry serviceRegistry=new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    private static void initializeData() throws Exception {
        /******************************************************************/
        totalordertest totalordertest1=new totalordertest();
        totalordertest totalordertest2=new totalordertest();
        totalordertest totalordertest3=new totalordertest();
        totalordertest totalordertest4=new totalordertest();
        totalordertest totalordertest5=new totalordertest();
        totalordertest totalordertest6=new totalordertest();
        totalordertest totalordertest7=new totalordertest();
        session.save(totalordertest1);
        session.save(totalordertest2);
        session.save(totalordertest3);
        session.save(totalordertest4);
        session.save(totalordertest5);
        session.save(totalordertest6);
        session.save(totalordertest7);

        /******************************************************************/

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
        ParkingLot parkingLot1 = new ParkingLot(3, 8, 3, new byte[3*8*3]);
        ParkingLot parkingLot2 = new ParkingLot(3, 4, 3, new byte[3*4*3]);
        ParkingLot parkingLot3 = new ParkingLot(3, 4, 3, new byte[3*4*3]);
        ParkingLot parkingLot4 = new ParkingLot(3, 8, 3, new byte[3*8*3]);
        ParkingLot parkingLot5 = new ParkingLot(3, 4, 3, new byte[3*4*3]);
        ParkingLot parkingLot6 = new ParkingLot(3, 4, 3, new byte[3*4*3]);
        ParkingLot parkingLot7 = new ParkingLot(3, 8, 3, new byte[3*8*3]);
        parkingLot1.setNumberOfInactiveParkings(68);
        parkingLot1.setEmptySpots(4);

        parkingLot2.setNumberOfInactiveParkings(0);
        parkingLot2.setEmptySpots(32);
        parkingLot3.setNumberOfInactiveParkings(0);
        parkingLot3.setEmptySpots(32);
        parkingLot4.setNumberOfInactiveParkings(0);
        parkingLot4.setEmptySpots(72);
        parkingLot5.setNumberOfInactiveParkings(0);
        parkingLot5.setEmptySpots(32);
        parkingLot6.setNumberOfInactiveParkings(0);
        parkingLot6.setEmptySpots(32);
        parkingLot7.setNumberOfInactiveParkings(0);
        parkingLot7.setEmptySpots(72);


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
        matrix3d[1][2][0]=0;
        matrix3d[2][2][2]=0;
        matrix3d[1][1][1]=0;
        matrix3d[0][0][0]=0;
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

        byte[] matrix1d44 = parkingLot4.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d44 = new int[3][8][3];  // Create 3D matrix
        int index44 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d44[i][j][k] = matrix1d44[index44] & 0xff;  // Copy element from 1D array to 3D matrix
                    index44++;
                    matrix3d44[i][j][k] = 0;
                }
            }
        }
        matrix3d44[1][2][0]=0;
        matrix3d44[2][2][2]=0;
        matrix3d44[1][1][1]=0;
        int index244 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d44[index244] = (byte) matrix3d44[i][j][k];  // Copy element from 3D array to 1D array
                    index244++;
                }
            }
        }
        parkingLot4.setMatrix(matrix1d44);

        byte[] matrix1d77 = parkingLot7.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d77 = new int[3][8][3];  // Create 3D matrix
        int index77 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d77[i][j][k] = matrix1d77[index77] & 0xff;  // Copy element from 1D array to 3D matrix
                    index77++;
                    matrix3d77[i][j][k] = 0;
                }
            }
        }
        matrix3d77[1][2][0]=0;
        matrix3d77[2][2][2]=0;
        matrix3d77[1][1][1]=0;
        int index277 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d77[index277] = (byte) matrix3d77[i][j][k];  // Copy element from 3D array to 1D array
                    index277++;
                }
            }
        }
        parkingLot7.setMatrix(matrix1d77);

        byte[] matrix1d22 = parkingLot2.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d22 = new int[3][4][3];  // Create 3D matrix
        int index22 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d22[i][j][k] = matrix1d22[index22] & 0xff;  // Copy element from 1D array to 3D matrix
                    index22++;
                    matrix3d22[i][j][k] = 0;
                }
            }
        }
        matrix3d22[1][2][0]=0;
        matrix3d22[2][2][2]=0;
        matrix3d22[1][1][1]=0;
        int index222 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d22[index222] = (byte) matrix3d22[i][j][k];  // Copy element from 3D array to 1D array
                    index222++;
                }
            }
        }
        parkingLot2.setMatrix(matrix1d22);

        byte[] matrix1d33 = parkingLot3.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d33 = new int[3][4][3];  // Create 3D matrix
        int index33 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d33[i][j][k] = matrix1d33[index33] & 0xff;  // Copy element from 1D array to 3D matrix
                    index33++;
                    matrix3d33[i][j][k] = 0;
                }
            }
        }
        matrix3d33[1][2][0]=0;
        matrix3d33[2][2][2]=0;
        matrix3d33[1][1][1]=0;
        int index233 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d33[index233] = (byte) matrix3d33[i][j][k];  // Copy element from 3D array to 1D array
                    index233++;
                }
            }
        }
        parkingLot3.setMatrix(matrix1d33);

        byte[] matrix1d55 = parkingLot5.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d55 = new int[3][4][3];  // Create 3D matrix
        int index55 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d55[i][j][k] = matrix1d55[index55] & 0xff;  // Copy element from 1D array to 3D matrix
                    index55++;
                    matrix3d55[i][j][k] = 0;
                }
            }
        }
        matrix3d55[1][2][0]=0;
        matrix3d55[2][2][2]=0;
        matrix3d55[1][1][1]=0;
        int index255 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d55[index255] = (byte) matrix3d55[i][j][k];  // Copy element from 3D array to 1D array
                    index255++;
                }
            }
        }
        parkingLot5.setMatrix(matrix1d55);

        byte[] matrix1d66 = parkingLot6.getMatrix();  // Get the one-dimensional matrix
        int[][][] matrix3d66 = new int[3][4][3];  // Create 3D matrix
        int index66 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix3d66[i][j][k] = matrix1d66[index66] & 0xff;  // Copy element from 1D array to 3D matrix
                    index66++;
                    matrix3d66[i][j][k] = 0;

                }
            }
        }
        matrix3d66[1][2][0]=0;
        matrix3d66[2][2][2]=0;
        matrix3d66[1][1][1]=0;
        int index266 = 0;  // Index into 1D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 3; k++) {
                    matrix1d66[index266] = (byte) matrix3d66[i][j][k];  // Copy element from 3D array to 1D array
                    index266++;
                }
            }
        }
        parkingLot6.setMatrix(matrix1d66);
        //**********************

        parkingLot1.setCapacity(parkingLot1.getNumberOfRows()*parkingLot1.getNumberOfColumns()*parkingLot1.getDepth());
        parkingLot2.setCapacity(parkingLot2.getNumberOfRows()*parkingLot2.getNumberOfColumns()*parkingLot2.getDepth());
        parkingLot3.setCapacity(parkingLot3.getNumberOfRows()*parkingLot3.getNumberOfColumns()*parkingLot3.getDepth());
        parkingLot4.setCapacity(parkingLot4.getNumberOfRows()*parkingLot4.getNumberOfColumns()*parkingLot4.getDepth());
        parkingLot5.setCapacity(parkingLot5.getNumberOfRows()*parkingLot5.getNumberOfColumns()*parkingLot5.getDepth());
        parkingLot6.setCapacity(parkingLot6.getNumberOfRows()*parkingLot6.getNumberOfColumns()*parkingLot6.getDepth());
        parkingLot7.setCapacity(parkingLot7.getNumberOfRows()*parkingLot7.getNumberOfColumns()*parkingLot7.getDepth());

        //**********************


        LocalDateTime oneMinutesFromNow = LocalDateTime.now().plusMinutes(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String oneMinutesFromNowString = oneMinutesFromNow.format(formatter);

        LocalDateTime threeMinutesFromNow = LocalDateTime.now().plusMinutes(5);
        String threeMinutesFromNowString = threeMinutesFromNow.format(formatter);

        Order order = new Order(10 , parkingLot1 , oneMinutesFromNowString , threeMinutesFromNowString, "a@homtail.com" , 149);
        Order order2 =new Order(11,"aa",12,12,12,12,12,12,12,12,7,11,"123");
        session.save(order2);
        session.save(order);


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
        session.save(parkingLot4);
        session.save(parkingLot5);
        session.save(parkingLot6);
        session.save(parkingLot7);

        /*session.save(price1);
        session.save(price2);
        session.save(price3);*/

        ParkingLotManager parkingLotManager1 = new ParkingLotManager("Benjamin ", "Mitchell", "Benjamin.Mitchell@company.com", "1000", parkingLot1,"0");
        ParkingLotManager parkingLotManager2 = new ParkingLotManager("Olivia ", "Martinez", "Olivia.Martinez@company.com", "1001", parkingLot2,"0");
        ParkingLotManager parkingLotManager3 = new ParkingLotManager("Gabriel ", "Reynolds", "Gabriel.Reynolds@company.com", "1002", parkingLot3,"0");
        ParkingLotManager parkingLotManager4 = new ParkingLotManager("Isabella ", "Wong", "Isabella.Wong@company.com", "1003", parkingLot4,"0");
        ParkingLotManager parkingLotManager5 = new ParkingLotManager("Ada", "Ada", "Ada.Ada@company.com", "1004", parkingLot5,"0");
        ParkingLotManager parkingLotManager6 = new ParkingLotManager("Alexander ", "Singh", "Alexander.Singh@company.com", "1005", parkingLot6,"0");
        ParkingLotManager parkingLotManager7 = new ParkingLotManager("Sophia ", "Doe", "Sophia.Doe@company.com", "1006", parkingLot7,"0");
        ChainManager chainManager1 = new ChainManager("Jane" ,"Smith", "jane.smith@company.com" ,  "abcde" , "0");
        Admin customerService = new Admin("Bob", "Johnson", "bob.johnson@company.com", "100","0");
        Admin customerService2 = new Admin("Alice", "Mile", "Alice.Mile@company.com", "101","0");
        ParkingLotEmployee parkingLotEmployee1 = new ParkingLotEmployee("Emma", "Williams", "Emma.Williams@company.com", "10000",  parkingLot1,"0");
        ParkingLotEmployee parkingLotEmployee2 = new ParkingLotEmployee("Emily ", "Carter", "Emily.Carter@company.com", "10001",  parkingLot2,"0");
        ParkingLotEmployee parkingLotEmployee3 = new ParkingLotEmployee("Nathanial ", "Robinson", "Nathanial.Robinson@company.com", "10002",  parkingLot3,"0");
        ParkingLotEmployee parkingLotEmployee4 = new ParkingLotEmployee("Ava ", "Jackson", "Ava.Jackson@company.com", "10003",  parkingLot4,"0");
        ParkingLotEmployee parkingLotEmployee5 = new ParkingLotEmployee("Liam ", "Lee", "Liam.Lee@company.com", "10004",  parkingLot5,"0");
        ParkingLotEmployee parkingLotEmployee6 = new ParkingLotEmployee("Victoria ", "Mores", "Victoria.Mores@company.com", "10005",  parkingLot6,"0");
        ParkingLotEmployee parkingLotEmployee7 = new ParkingLotEmployee("William ", "Germa", "William.Germa@company.com", "10006",  parkingLot7,"0");

        String encryptedString_ = encrypt(chainManager1.getPassword() , secretKey);
        chainManager1.setPassword(encryptedString_);

        String encryptedString_1 = encrypt(customerService.getPassword() , secretKey);
        customerService.setPassword(encryptedString_1);
        String encryptedString_2 = encrypt(customerService2.getPassword() , secretKey);
        customerService2.setPassword(encryptedString_2);


        String encryptedString11 = encrypt(parkingLotEmployee1.getPassword() , secretKey);
        parkingLotEmployee1.setPassword(encryptedString11);
        String encryptedString12 = encrypt(parkingLotEmployee2.getPassword() , secretKey);
        parkingLotEmployee2.setPassword(encryptedString12);
        String encryptedString13 = encrypt(parkingLotEmployee3.getPassword() , secretKey);
        parkingLotEmployee3.setPassword(encryptedString13);
        String encryptedString14 = encrypt(parkingLotEmployee4.getPassword() , secretKey);
        parkingLotEmployee4.setPassword(encryptedString14);
        String encryptedString15 = encrypt(parkingLotEmployee5.getPassword() , secretKey);
        parkingLotEmployee5.setPassword(encryptedString15);
        String encryptedString16 = encrypt(parkingLotEmployee6.getPassword() , secretKey);
        parkingLotEmployee6.setPassword(encryptedString16);
        String encryptedString17 = encrypt(parkingLotEmployee7.getPassword() , secretKey);
        parkingLotEmployee7.setPassword(encryptedString17);


        String encryptedString1 = encrypt(parkingLotManager1.getPassword() , secretKey);
        parkingLotManager1.setPassword(encryptedString1);
        String encryptedString2 = encrypt(parkingLotManager2.getPassword() , secretKey);
        parkingLotManager2.setPassword(encryptedString2);
        String encryptedString3 = encrypt(parkingLotManager3.getPassword() , secretKey);
        parkingLotManager3.setPassword(encryptedString3);
        String encryptedString4 = encrypt(parkingLotManager4.getPassword() , secretKey);
        parkingLotManager4.setPassword(encryptedString4);
        String encryptedString5 = encrypt(parkingLotManager5.getPassword() , secretKey);
        parkingLotManager5.setPassword(encryptedString5);
        String encryptedString6 = encrypt(parkingLotManager6.getPassword() , secretKey);
        parkingLotManager6.setPassword(encryptedString6);
        String encryptedString7 = encrypt(parkingLotManager7.getPassword() , secretKey);
        parkingLotManager7.setPassword(encryptedString7);

        session.save(parkingLotManager1);
        session.save(parkingLotManager2);
        session.save(parkingLotManager3);
        session.save(parkingLotManager4);
        session.save(parkingLotManager5);
        session.save(parkingLotManager6);
        session.save(parkingLotManager7);
        session.save(chainManager1);
        session.save(customerService);
        session.save(customerService2);
        session.save(parkingLotEmployee1);
        session.save(parkingLotEmployee2);
        session.save(parkingLotEmployee3);
        session.save(parkingLotEmployee4);
        session.save(parkingLotEmployee5);
        session.save(parkingLotEmployee6);
        session.save(parkingLotEmployee7);

        FullSubscriber subscriber = new FullSubscriber(149,"Jane", "Smith", "jane.smith@company.com", "2", "111","0");
        String x = encrypt(subscriber.getPassword() , secretKey);
        subscriber.setPassword(x);
        FullSubscriber subscriber1 = new FullSubscriber(11, "John", "Doe", "john.doe@email.com", "11", "555", "0");
        String encryptedPassword1 = encrypt(subscriber1.getPassword(), secretKey);
        subscriber1.setPassword(encryptedPassword1);
        subscriber1.setSubscriptionExpiryDate(LocalDateTime.now().plusDays(2));
        FullSubscriber subscriber2 = new FullSubscriber(12, "Sarah", "Johnson", "sarah.johnson@domain.com", "12", "123", "0");
        String encryptedPassword2 = encrypt(subscriber2.getPassword(), secretKey);
        subscriber2.setPassword(encryptedPassword2);
        FullSubscriber subscriber3 = new FullSubscriber(13, "Michael", "Williams", "michael.williams@organization.com", "13", "789", "0");
        String encryptedPassword3 = encrypt(subscriber3.getPassword(), secretKey);
        subscriber3.setPassword(encryptedPassword3);
        FullSubscriber subscriber4 = new FullSubscriber(14, "Emily", "Jones", "emily.jones@enterprise.com", "14", "456", "0");
        String encryptedPassword4 = encrypt(subscriber4.getPassword(), secretKey);
        subscriber4.setPassword(encryptedPassword4);


        RegularSubscriber regularSubscriber = new RegularSubscriber(123,"John", "Smith", "jane.smith@company.com", "1", "333","0");
        x = encrypt(regularSubscriber.getPassword() , secretKey);
        regularSubscriber.setPassword(x);
        RegularSubscriber regularSubscriber2 = new RegularSubscriber(15,"Eva", "Smith", "Eva.smith@company.com", "15", "333","0");
        x = encrypt(regularSubscriber2.getPassword() , secretKey);
        regularSubscriber2.setPassword(x);


        Car car1 = new Car(2132456);
        Car car2 = new Car(4525242);
        Car car3 = new Car(5132456);
        Car car4 = new Car(9925242);
        Car car5 = new Car(2179456);
        Car car6 = new Car(4525223);
        Car car7 = new Car(9132106);
        Car car8 = new Car(4585245);
        Car car9 = new Car(4432106);
        Car car10 = new Car(5871245);
        session.save(subscriber);
        session.save(subscriber1);
        session.save(subscriber2);
        session.save(subscriber3);
        session.save(subscriber4);
        session.save(regularSubscriber);
        session.save(regularSubscriber2);

        session.save(car1);
        session.save(car2);
        session.save(car3);
        session.save(car4);
        session.save(car5);
        session.save(car6);
        session.save(car7);
        session.save(car8);
        session.save(car9);
        session.save(car10);


        subscriber.addCar(car1);
        subscriber.addCar(car2);
        subscriber.addCar(car3);
        subscriber1.addCar(car4);
        subscriber2.addCar(car5);
        subscriber3.addCar(car6);
        subscriber4.addCar(car8);
        regularSubscriber.addCar(car7);
        regularSubscriber2.addCar(car9);
        regularSubscriber.addCar(car10);



        Complaint c1=new Complaint("The parking spaces are too narrow, making it difficult to park my car.",1,149);
        Complaint c2=new Complaint("The parking lot is poorly lit, making it unsafe to walk to my car at night",1,123);
        Complaint c3=new Complaint("The parking lot is always full, and there are never any available spaces.",7,13);
        session.save(c1);
        session.save(c2);
        session.save(c3);
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
        Time x1=new Time(4,5,6);

        for (Order xi:x) {
            if (xi.getSubId() == 0 && !xi.isAlreadyInParkingLot() && xi.getEnterHour() == t.getHour() && xi.getEnterYear() == t.getYear() && xi.getEnterDay() == t.getDayOfMonth() && xi.getEnterMonth() == t.getMonthValue())
                SendEmail.SendEmail(xi.getEmail(), "Customer didn't arrive! ", "hello, we would like to alert you that you have ordered a parking spot from hour:  " + xi.getEnterHour() + " to hour:  " + xi.getExitHour() + " ,the order will be cancelled if you didnt arrive in 30 minutes! Thank you ");
        }
       // session.close();
    }

}
