package il.cshaifasweng.OCSFMediatorExample.client;

public class CurrentPictureEvent {
    int[][][] currentParkingLot;
    int currentParkingLotCols;

    public int[][][] getCurrentParkingLot() {
        return currentParkingLot;
    }

    public void setCurrentParkingLot(int[][][] currentParkingLot) {
        this.currentParkingLot = currentParkingLot;
    }

    public int getCurrentParkingLotCols() {
        return currentParkingLotCols;
    }

    public void setCurrentParkingLotCols(int currentParkingLotCols) {
        this.currentParkingLotCols = currentParkingLotCols;
    }

    public CurrentPictureEvent(int[][][] currentParkingLot, int currentParkingLotCols) {
        this.currentParkingLot = currentParkingLot;
        this.currentParkingLotCols = currentParkingLotCols;
    }
}
