package il.cshaifasweng.OCSFMediatorExample.entities;


import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Parking implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int hight=3;
    private int width;
    private int depth=3;
    private int size;
    public Parking(String n,int width) {
        this.name=n;
        this.width=width;
        this.size=hight*depth*width;
    }

    public Parking() {

    }
}
