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

    public CanceledOrder(int orderId, double refundedMoney, int subId) {
        this.orderId = orderId;
        this.refundedMoney = refundedMoney;
        this.subId = subId;
    }

    public CanceledOrder() {

    }
}
