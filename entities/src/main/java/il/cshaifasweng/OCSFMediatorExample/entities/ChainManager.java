package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "chainmanager")
public class ChainManager implements Serializable {

    private static final long serialVersionUID = 1035377024343093717L;

    @Id
    private final int id = 999999999;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String isConnected;


    public ChainManager(String firstName, String lastName, String email, String password,String x) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isConnected=x;
    }

    public ChainManager() {

    }

}
