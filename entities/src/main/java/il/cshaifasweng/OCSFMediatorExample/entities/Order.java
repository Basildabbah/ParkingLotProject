package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 7030377024343883717L;

    @Id
    private int id;

    private int OrderId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkinglot;

    private String TypeOfOrder;

    private int EnterHour;

    private int EnterDay;

    private int EnterMonth;

    private int EnterYear;

    private int ExitHour;

    private int ExitDay;

    private int ExitMonth;

    private int ExitYear;

    private int carNumber;

    private String entryTime;

    private String exitTime;

    private double payment;

    private int subId;

    private String email;

    private int PersonId;

    private boolean alreadyInParkingLot;

    private int isConnected;

    private String Password;


    public Order() {

    }

    public Order(int carNumber, ParkingLot parkinglot, String entryTime, String exitTime,String email, int subId) {
        this.carNumber = carNumber;
        setParkinglot(parkinglot);
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.email = email;
        this.subId = subId;
        this.isConnected=0;
    }

    public Order(int OrderId,String TypeOfOrder,int EnterHour, int EnterDay, int EnterMonth, int EnterYear , int ExitHour,
                 int ExitDay, int ExitMonth, int ExitYear, ParkingLot parkinglot, int PersonId, String Password) {
        this.OrderId = OrderId;
        this.id = OrderId;
        this.TypeOfOrder = TypeOfOrder;
        this.EnterHour = EnterHour;
        this.EnterDay = EnterDay;
        this.EnterMonth = EnterMonth;
        this.EnterYear = EnterYear;
        this.ExitHour = ExitHour;
        this.ExitDay = ExitDay;
        this.ExitMonth = ExitMonth;
        this.ExitYear = ExitYear;
        this.PersonId = PersonId;
        this.Password = Password;
        setParkinglot(parkinglot);
        this.isConnected=0;
    }

    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
        parkinglot.getAllOrders().add(this);
    }

}
