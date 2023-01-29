package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "chainmanager")
public class ChainManager implements Serializable {

    private static final long serialVersionUID = 1035377024343093717L;
    private static ChainManager instance;

    @Id
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String isConnected;

    public ChainManager(String firstName, String lastName, String email, String password,String x) {
        this.id = 999999999;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isConnected=x;
    }

    public ChainManager() {

    }

    public static ChainManager getInstance() {
        if (instance == null) {
            instance = new ChainManager();
        }
        return instance;
    }
}
