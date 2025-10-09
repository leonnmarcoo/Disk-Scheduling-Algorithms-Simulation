package Backend;

public class DiskRequest {
    private int trackNumber;
    private int arrivalTime;
    
    public DiskRequest(int trackNumber, int arrivalTime) {
        this.trackNumber = trackNumber;
        this.arrivalTime = arrivalTime;
    }
    
    public int getTrackNumber() {
        return trackNumber;
    }
    
    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }
    
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
    
    @Override
    public String toString() {
        return String.valueOf(trackNumber);
    }
}
