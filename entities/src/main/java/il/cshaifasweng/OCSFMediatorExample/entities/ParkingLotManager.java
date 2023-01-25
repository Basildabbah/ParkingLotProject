package il.cshaifasweng.OCSFMediatorExample.entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "parkinglotmanagers")
public class ParkingLotManager implements Serializable {

    private static final long serialVersionUID = 7030377025543093717L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;



    private String isConnected;
    @OneToOne(mappedBy = "parkinglotmanager")
    private ParkingLot parkinglot;

    public ParkingLotManager(String firstName, String lastName, String email, String password,ParkingLot parkinglot,String x) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isConnected=x;
        setParkingLot(parkinglot);
    }

    public ParkingLotManager() {
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkinglot = parkingLot;
        parkingLot.setParkinglotmanager(this);
    }
}
