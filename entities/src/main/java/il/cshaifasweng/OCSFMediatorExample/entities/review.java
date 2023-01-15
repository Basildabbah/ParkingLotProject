package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "review")
public class review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    String name;
    String text;
    String star;

    public review() {
    }

    public review(String name1, String text1, String star1) {
        this.name = name1;
        this.text = text1;
        this.star = star1;
    }
}

