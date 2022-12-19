package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String typeOfParking;
    private String PAymentMethoud;
    private int price;

    public Price(String typeOfParking, String PAymentMethoud, int price) {
        this.typeOfParking = typeOfParking;
        this.PAymentMethoud = PAymentMethoud;
        this.price = price;
    }

    public Price() {

    }
}