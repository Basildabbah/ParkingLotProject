package il.cshaifasweng.OCSFMediatorExample.client;

public class CPCrefresh {
    int[][][] currentParkingLot;

    public CPCrefresh(int[][][] object1) {
        this.currentParkingLot = object1;
    }

    public int[][][] getCurrentParkingLot() {
        return currentParkingLot;
    }

    public void setCurrentParkingLot(int[][][] currentParkingLot) {
        this.currentParkingLot = currentParkingLot;
    }
}
