package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "test")
public class test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String email;


    private String occupation;



    public test(String email, String occupation) {
        this.email = email;

        this.occupation = occupation;

    }

    public test() {

    }
}
