package il.cshaifasweng.OCSFMediatorExample.entities;

import lombok.Data;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "parkinglots")
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int rows;

    private int columns;

    private int depth;

    //deserialize and serialize after
    @Lob
    @Column(name = "matrix", columnDefinition = "BLOB")
    private byte[] matrix;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "parkinglot")
    private List<Order> allOrders;

    public ParkingLot(int rows, int columns, int depth, byte[] matrix) {
        super();
        this.rows = rows;
        this.columns = columns;
        this.depth = depth;
        this.matrix = matrix;
    }

    public ParkingLot() {

    }
}
