package Backend;

import java.util.ArrayList;
import java.util.List;

public class FCFSScheduler implements DiskScheduler {
    
    @Override
    public String getAlgorithmName() {
        return "FCFS (First-Come, First-Served)";
    }
    
    @Override
    public SchedulingResult schedule(List<DiskRequest> requests, int initialHeadPosition, int diskSize) {
        List<Integer> seekSequence = new ArrayList<>();
        List<Integer> seekDistances = new ArrayList<>();
        
        seekSequence.add(initialHeadPosition);
        int currentPosition = initialHeadPosition;
        int totalSeekTime = 0;
        
        // Process requests in the order they arrive
        for (DiskRequest request : requests) {
            int trackNumber = request.getTrackNumber();
            seekSequence.add(trackNumber);
            
            int seekDistance = Math.abs(trackNumber - currentPosition);
            seekDistances.add(seekDistance);
            totalSeekTime += seekDistance;
            
            currentPosition = trackNumber;
        }
        
        double averageSeekTime = requests.isEmpty() ? 0 : (double) totalSeekTime / requests.size();
        
        return new SchedulingResult(seekSequence, totalSeekTime, averageSeekTime, seekDistances);
    }
}
