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



    private int DayOfCancel;

    //private int orderId;

    private String refundedMoney;

    private int subId;

    public CanceledOrder(int orderId, String refundedMoney, int subId) {
        //this.orderId = orderId;
        this.refundedMoney = refundedMoney;
        this.subId = subId;
    }
    private int ParkingLotId;
    private int OrderId;
    private  String TypeOfOrder;
    private int EnterHour;
    private int EnterDay;
    private int EnterMonth;
    private int EnterYear;
    private int ExitHour;
    private int ExitDay;
    private int ExitMonth;
    private int ExitYear;
    private int PersonId;
    private  String Password;
    public CanceledOrder() {

    }

    public CanceledOrder(int orderId, String typeOfOrder, int enterHour, int enterDay, int enterMonth, int enterYear, int exitHour, int exitDay, int exitMonth, int exitYear, int parkingLotId, int personId, String password) {
        this.OrderId = orderId;
        this.TypeOfOrder = typeOfOrder;
        this.EnterHour = enterHour;
        this.EnterDay = enterDay;
        this.EnterMonth = enterMonth;
        this.EnterYear = enterYear;
        this.ExitHour = exitHour;
        this.ExitDay = exitDay;
        this.ExitMonth = exitMonth;
        this.ExitYear = exitYear;
        this.ParkingLotId = parkingLotId;
        this.PersonId = personId;
        this.Password = password;
        this.id = orderId;

    }
}