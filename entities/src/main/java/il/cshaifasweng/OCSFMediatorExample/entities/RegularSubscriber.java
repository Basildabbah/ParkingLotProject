package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "regularsubscribers")
public class RegularSubscriber {

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String visaCard;

    private String timeOfEntrance;

    private String timeOfExit;

    private int parkingLotId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriberId;

    private String password;

    private int remainingHours;

    private LocalDateTime subscriptionExpiryDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "regularsubscriber")
    private List<Car> carList;

    private boolean flagDay;
    public RegularSubscriber() {
    }

    public RegularSubscriber(int id, String firstName, String lastName, String email, String password , String visaCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.visaCard = visaCard;
        this.password = password;
        LocalDateTime now = LocalDateTime.now();
        subscriptionExpiryDate = now.plusDays(28);
        //??
        this.remainingHours = 14;
        this.carList = new ArrayList<Car>();
        this.flagDay = false;
    }

        public void addCar(Car car){
        this.carList.add(car);
    }

    public void removeCar(Car car){
        carList.remove(car);
    }
}
