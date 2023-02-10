package il.cshaifasweng.OCSFMediatorExample.client;

public class CP2Crefresh {
    int[][][] currentParkingLot;

    public CP2Crefresh(int[][][] object1) {
        this.currentParkingLot = object1;
    }

    public int[][][] getCurrentParkingLot() {
        return currentParkingLot;
    }

    public void setCurrentParkingLot(int[][][] currentParkingLot) {
        this.currentParkingLot = currentParkingLot;
    }
}
