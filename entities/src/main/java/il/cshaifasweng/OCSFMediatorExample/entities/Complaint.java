package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "complaints")
public class Complaint {

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
