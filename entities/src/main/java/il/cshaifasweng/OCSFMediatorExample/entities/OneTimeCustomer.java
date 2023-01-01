package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "onetimecustomers")
public class OneTimeCustomer extends Client{

    @OneToOne(mappedBy = "onetimecustomer")
    private Car car;

}
