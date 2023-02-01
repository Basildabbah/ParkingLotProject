package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "parkinglots")
public class ParkingLot implements Serializable {

    private static final long serialVersionUID = 7030377114343093717L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int Capacity;

    private int numberOfRows;

    private int numberOfColumns;

    private int depth;

    private int emptySpots;

    private int numberOfOrders;

    private int numberOfCancelledOrders;

    private int numberOfLateArrivals;

    private int numberOfInactiveParkings;

    private int NumberOfComplaints;


    //deserialize and serialize after
    @Lob
    @Column(name = "matrix", columnDefinition = "BLOB")
    private byte[] matrix;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parkinglotmanager_id")
    private ParkingLotManager parkinglotmanager;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prices_id")
    private Prices price;

    //this field will contain all the orders that are currently registered to this parking lot
    //which means it will include all the cars tha are parked at the moment in addition to all the reservations that have been made
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parkinglot")
    private List<Order> allOrders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parkinglot")
    private List<ParkingLotEmployee> parkingLotEmployeeList;

    public ParkingLot(int rows, int columns, int depth, byte[] matrix) {
        super();
        this.numberOfRows = rows;
        this.numberOfColumns = columns;
        this.depth = depth;
        this.matrix = matrix;
        emptySpots = rows * columns * depth;
        numberOfOrders = 0;
        numberOfInactiveParkings = 0;
        numberOfCancelledOrders = 0;
        numberOfLateArrivals = 0;
        this.allOrders = new ArrayList<Order>();
        this.parkingLotEmployeeList = new ArrayList<ParkingLotEmployee>();
    }

    public ParkingLot() {

    }
}