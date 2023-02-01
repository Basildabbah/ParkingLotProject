package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "admins")
public class Admin implements Serializable {

    private static final long serialVersionUID = 1035377024343093717L;

    private static int counter = 100;

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


    private String isConnected;

    public Admin(String firstName, String lastName, String email, String password,String x) {
        this.id = counter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isConnected=x;
    }

    public Admin() {

    }
}
