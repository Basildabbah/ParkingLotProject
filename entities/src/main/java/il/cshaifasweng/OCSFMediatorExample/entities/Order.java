package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 4412085390748840422L;

    @Id
    private int id;

    @OneToOne(mappedBy = "order")
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglot_id")
    private ParkingLot parkinglot;

    private int OrderId;

    private String TypeOfOrder;

    private int EnterHour;

    private int EnterDay;

    private int EnterMonth;

    private int EnterYear;

    private int ExitHour;

    private int ExitDay;

    private int ExitMonth;

    private int ExitYear;

    private int ParkingLotId;

    private int PersonId;

    private String Password;

    private String Email;

    private int CarNumber;

    private int EnterMinute;

    private int ExitMinute;
    public Order() {
    }

    public Order(int OrderId,String TypeOfOrder,int EnterHour, int EnterDay, int EnterMonth, int EnterYear , int ExitHour, int ExitDay, int ExitMonth, int ExitYear,int ParkingLotId, int PersonId, String Password,int etermin,int exitmin) {
        this.OrderId = OrderId;
        this.TypeOfOrder = TypeOfOrder;
        this.EnterHour = EnterHour;
        this.EnterDay = EnterDay;
        this.EnterMonth = EnterMonth;
        this.EnterYear = EnterYear;
        this.ExitHour = ExitHour;
        this.ExitDay = ExitDay;
        this.ExitMonth = ExitMonth;
        this.ExitYear = ExitYear;
        this.ParkingLotId = ParkingLotId;
        this.PersonId = PersonId;
        this.Password = Password;
        this.id = OrderId;
        this.EnterMinute=etermin;
        this.ExitMinute=exitmin;
    }

    public Order(int OrderId,String TypeOfOrder,int EnterHour, int EnterDay, int EnterMonth, int EnterYear , int ExitHour, int ExitDay, int ExitMonth, int ExitYear,int ParkingLotId, int PersonId, String Password) {
        this.OrderId = OrderId;
        this.TypeOfOrder = TypeOfOrder;
        this.EnterHour = EnterHour;
        this.EnterDay = EnterDay;
        this.EnterMonth = EnterMonth;
        this.EnterYear = EnterYear;
        this.ExitHour = ExitHour;
        this.ExitDay = ExitDay;
        this.ExitMonth = ExitMonth;
        this.ExitYear = ExitYear;
        this.ParkingLotId = ParkingLotId;
        this.PersonId = PersonId;
        this.Password = Password;
        this.id = OrderId;
    }
    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
        parkinglot.getAllOrders().add(this);
    }

    //**************************

    private boolean alreadyInParkingLot;

    private int LateArrival;

    private String entryTime;

    private String exitTime;

    private double payment;

    private int IsConnected;


    // ??
    private int subId;
    public Order(int carNumber, ParkingLot parkinglot, String entryTime, String exitTime,String email, int subId) {
        this.CarNumber = carNumber;
        setParkinglot(parkinglot);
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.Email = email;
        this.subId = subId;
        this.alreadyInParkingLot = false;
    }

}