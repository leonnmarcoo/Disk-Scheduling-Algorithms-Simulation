package Backend;

import java.util.ArrayList;
import java.util.List;

public class SSTFScheduler implements DiskScheduler {
    
    @Override
    public String getAlgorithmName() {
        return "SSTF (Shortest Seek Time First)";
    }
    
    @Override
    public SchedulingResult schedule(List<DiskRequest> requests, int initialHeadPosition, int diskSize) {
        List<Integer> seekSequence = new ArrayList<>();
        List<Integer> seekDistances = new ArrayList<>();
        List<DiskRequest> remainingRequests = new ArrayList<>(requests);
        
        seekSequence.add(initialHeadPosition);
        int currentPosition = initialHeadPosition;
        int totalSeekTime = 0;
        
        while (!remainingRequests.isEmpty()) {
            DiskRequest closest = null;
            int minDistance = Integer.MAX_VALUE;
            
            for (DiskRequest request : remainingRequests) {
                int distance = Math.abs(request.getTrackNumber() - currentPosition);
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = request;
                }
            }
            
            if (closest != null) {
                seekSequence.add(closest.getTrackNumber());
                seekDistances.add(minDistance);
                totalSeekTime += minDistance;
                currentPosition = closest.getTrackNumber();
                remainingRequests.remove(closest);
            }
        }
        
        double averageSeekTime = requests.isEmpty() ? 0 : (double) totalSeekTime / requests.size();
        
        return new SchedulingResult(seekSequence, totalSeekTime, averageSeekTime, seekDistances);
    }
}
