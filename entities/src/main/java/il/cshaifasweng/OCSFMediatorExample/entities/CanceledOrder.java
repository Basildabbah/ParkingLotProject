package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "canceledorders")
public class CanceledOrder implements Serializable {

    private static final long serialVersionUID = 7770377024343093717L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int ParkingLotId;

    private int DayOfCancel;

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