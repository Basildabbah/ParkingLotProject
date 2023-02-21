package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "inactivenumber")
public class inactivenumber implements Serializable {

    private static final long serialVersionUID = 4412085390748840471L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int parkingLotId;
    private LocalDateTime date;

    public inactivenumber() {
    }
    public inactivenumber(int parkingLotId) {
        this.parkingLotId = parkingLotId;
        this.date=LocalDateTime.now();
    }
}
