package il.cshaifasweng.OCSFMediatorExample.entities;


import lombok.*;
import javax.persistence.*;

@Data
@Entity
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int hight=3;
    private int width;
    private int depth=3;

    public Parking(String n,int width) {
        this.name=n;
        this.width=width;
    }

    public Parking() {

    }
}
