package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "complaints")
public class Complaint implements Serializable {

    private static final long serialVersionUID = 4412085390748840478L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String complaintMessage;

    private String response;

    private int parkingLotId;

    private String status;

    private double refund;

    public Complaint() {
    }

    public Complaint(String complaintMessage,int parkingLotId) {
        this.complaintMessage = complaintMessage;
        this.parkingLotId = parkingLotId;
        this.status = "no response yet";
    }
}
