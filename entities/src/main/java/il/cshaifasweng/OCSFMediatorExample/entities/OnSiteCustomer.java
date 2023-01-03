package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "onsitecustomers")
public class OnSiteCustomer {
    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String visaCard;

    private String timeOfEntrance;

    private String timeOfExit;

    private int parkingLotId;

    @OneToOne(mappedBy = "onsitecustmoer")
    private Car car;

    public OnSiteCustomer() {
    }

    public OnSiteCustomer(int id, String firstName, String lastName, String email, String visaCard,Car car) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.visaCard = visaCard;
        setCar(car);
    }

    public void setCar(Car car){
        this.car = car;
        car.setOnsitecustmoer(this);
    }
}
