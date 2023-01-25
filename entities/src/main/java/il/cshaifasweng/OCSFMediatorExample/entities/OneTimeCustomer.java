package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "onetimecustomers")
public class OneTimeCustomer implements Serializable {

    private static final long serialVersionUID = 7030377025543093717L;

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String visaCard;

    private String timeOfEntrance;

    private String timeOfExit;

    private int parkingLotId;

    @OneToOne(mappedBy = "onetimecustomer")
    private Car car;

    public OneTimeCustomer() {
    }

    public OneTimeCustomer(int id, String firstName, String lastName, String email, String visaCard,Car car) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.visaCard = visaCard;
        setCar(car);
    }

    public void setCar(Car car){
        this.car = car;
        car.setOnetimecustomer(this);
    }
}
