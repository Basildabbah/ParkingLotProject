package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "parkinglotemployees")
public class ParkingLotEmployee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkinglot;

    public ParkingLotEmployee(String firstName, String lastName, String email, String password, ParkingLot parkinglot) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        setParkingLot(parkinglot);
    }

    public ParkingLotEmployee() {
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkinglot = parkingLot;
        parkingLot.getParkingLotEmployeeList().add(this);
    }
}
