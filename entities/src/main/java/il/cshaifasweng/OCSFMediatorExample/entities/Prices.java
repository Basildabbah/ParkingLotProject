package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prices")
public class Prices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection
    private List<String> typeOfParking;

    @ElementCollection
    private List<String> paymentMethod;

    @ElementCollection
    private List<String> valueNote;


    @OneToOne(mappedBy = "price")
    private ParkingLot parkinglot;


    public Prices() {
    }

    public Prices(List<String> typeOfParking, List<String> paymentMethod, List<String> valueNote , ParkingLot parkinglot) {
        this.typeOfParking = typeOfParking;
        this.paymentMethod = paymentMethod;
        this.valueNote = valueNote;
        setParkinglot(parkinglot);
    }

    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
        parkinglot.setPrice(this);
    }
}
