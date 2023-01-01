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
    @JoinColumn(name = "oneTimeCustomer_id", referencedColumnName = "id")
    private OneTimeCustomer oneTimeCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "onSiteCustomer_id", referencedColumnName = "id")
    private OnSiteCustomer onSiteCustomer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "subscriber_id")
    private Subscriber subscriber;


}
