package Backend;

import java.util.List;

public class SchedulingResult {
    private List<Integer> seekSequence;
    private int totalSeekTime;
    private double averageSeekTime;
    private List<Integer> seekDistances;
    
    public SchedulingResult(List<Integer> seekSequence, int totalSeekTime, 
                           double averageSeekTime, List<Integer> seekDistances) {
        this.seekSequence = seekSequence;
        this.totalSeekTime = totalSeekTime;
        this.averageSeekTime = averageSeekTime;
        this.seekDistances = seekDistances;
    }
    
    public List<Integer> getSeekSequence() {
        return seekSequence;
    }
    
    public int getTotalSeekTime() {
        return totalSeekTime;
    }
    
    public double getAverageSeekTime() {
        return averageSeekTime;
    }
    
    public List<Integer> getSeekDistances() {
        return seekDistances;
    }
}
