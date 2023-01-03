package il.cshaifasweng.OCSFMediatorExample.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parkinglotmanagers")
public class ParkingLotManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @OneToOne(mappedBy = "parkinglotmanager")
    private ParkingLot parkinglot;

    public ParkingLotManager(String firstName, String lastName, String email, String password,ParkingLot parkinglot) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        setParkingLot(parkinglot);
    }

    public ParkingLotManager() {
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkinglot = parkingLot;
        parkingLot.setParkinglotmanager(this);
    }
}
