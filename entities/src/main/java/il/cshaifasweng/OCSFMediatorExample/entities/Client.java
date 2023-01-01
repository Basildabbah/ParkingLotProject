package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String visaCard;

    private String timeOfEntrance;

    private String timeOfExit;

    private int parkingLotId;
}
