package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "regularsubscribers")
public class RegularSubscriber extends Subscriber{

    private boolean flagDay;
}
