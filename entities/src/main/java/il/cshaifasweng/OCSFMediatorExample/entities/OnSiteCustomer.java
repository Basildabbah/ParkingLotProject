package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "onsitecustomers")
public class OnSiteCustomer extends Client{

    @OneToOne(mappedBy = "onsitecustomer")
    private Car car;

}
