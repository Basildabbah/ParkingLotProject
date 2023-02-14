package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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

    private LocalDateTime date;

    private int parkingLotId;

    private String status;

    private double refund;

    private int userId;

    public Complaint() {
    }

    public Complaint(String complaintMessage,int parkingLotId , int userid) {
        this.complaintMessage = complaintMessage;
        this.parkingLotId = parkingLotId;
        this.status = "no response yet";
        this.userId = userid;
        this.date=LocalDateTime.now();
    }
}
