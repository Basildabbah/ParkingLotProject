package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.*;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public Order() {
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
    }

    public void setParkinglot(ParkingLot parkinglot) {
        this.parkinglot = parkinglot;
        parkinglot.getAllOrders().add(this);
    }

    //******************************************************************************

    private boolean AlreadyInParkingLot;

    private String entryTime;

    private String exitTime;

    private double payment;

    private int IsConnected;


    // ??
    private int subId;
}
