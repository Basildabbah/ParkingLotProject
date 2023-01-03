//package il.cshaifasweng.OCSFMediatorExample.entities;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@Table(name = "subscribers")
//public class Subscriber extends Client{
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int subscriberId;
//
//    private String password;
//
//    private int remainingHours;
//
//    private LocalDateTime subscriptionExpiryDate;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
//    private List<Car> carList;
//
//    public Subscriber() {
//    }
//
//    public Subscriber(int id, String firstName, String lastName, String email, String visaCard,String password) {
//        super(id, firstName, lastName, email, visaCard);
//        this.password = password;
//        LocalDateTime now = LocalDateTime.now();
//        subscriptionExpiryDate = now.plusDays(28);
//        this.remainingHours = remainingHours;
//        this.carList = new ArrayList<Car>();
//    }
//
//    public void addCar(Car car){
//        this.carList.add(car);
//    }
//
//    public void removeCar(Car car){
//        carList.remove(car);
//    }
//}
