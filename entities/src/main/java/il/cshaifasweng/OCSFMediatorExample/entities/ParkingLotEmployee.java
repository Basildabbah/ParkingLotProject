package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "parkinglotemployees")
public class ParkingLotEmployee implements Serializable {

    private static final long serialVersionUID = 7030377024343078717L;

    private static int counter = 10000;

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkinglot;

    private String isConnected;

    public ParkingLotEmployee(String firstName, String lastName, String email, String password, ParkingLot parkinglot) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        setParkingLot(parkinglot);
        isConnected = "0";
        this.id = counter++;
    }

    public ParkingLotEmployee() {
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkinglot = parkingLot;
        parkingLot.getParkingLotEmployeeList().add(this);
    }
}
