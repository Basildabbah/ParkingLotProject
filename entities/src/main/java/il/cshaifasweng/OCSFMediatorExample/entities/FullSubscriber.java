package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fullsubscirbers")
public class FullSubscriber extends Subscriber{

}
