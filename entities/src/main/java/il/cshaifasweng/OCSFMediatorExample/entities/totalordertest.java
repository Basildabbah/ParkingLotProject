package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "totalordertest")
public class totalordertest implements Serializable{

    private static final long serialVersionUID = 7030377024343017719L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    int guest;
    int full;
    int reg;

    public totalordertest() {
        guest=0;
        full=0;
        reg=0;
    }

    public totalordertest(int guest1, int full1, int reg1) {
        this.guest=guest1;
        this.full=full1;
        this.reg=reg1;

    }
}

