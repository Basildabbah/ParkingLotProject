package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int carNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onetimecustomer_id")
    private OneTimeCustomer onetimecustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onsitecustomer_id")
    private OnSiteCustomer onsitecustmoer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fullsubscriber_id")
    private FullSubscriber fullsubscriber;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "regularsubscriber_id")
    private RegularSubscriber regularsubscriber;

    public Car() {
    }

    public Car(int carNumber) {
        this.carNumber = carNumber;
    }
}
