package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "subscribers")
public class Subscriber extends Client{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subscriberId;

    private String password;

    private int remainingHours;

    private String subscriptionExpiryDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subscriber")
    private List<Car> carList;
}
