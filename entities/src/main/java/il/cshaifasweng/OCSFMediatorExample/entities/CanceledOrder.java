package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "canceledorders")
public class CanceledOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;

    private double refundedMoney;

    private int subId;

}
