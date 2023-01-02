package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "parkinglotemployees")
public class ParkingLotEmployee extends Admin{
    private int parkingLotId;

    public ParkingLotEmployee(String firstName, String lastName, String email, String password, String occupation, int parkingLotId) {
        super(firstName, lastName, email, password, occupation);
        this.parkingLotId = parkingLotId;
    }

    public ParkingLotEmployee() {
    }
}
