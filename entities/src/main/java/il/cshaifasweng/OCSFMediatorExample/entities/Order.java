package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(mappedBy = "order")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkinglot;


    private String entryTime;

    private String exitTime;

    private double payment;


    // ??
    private int subId;
    private String email;
    private int isConnected;

    public Order() {

    }
    public Order(Car c1,String email) {
        this.car=c1;
        this.email=email;
        this.isConnected=0;
    }
}
