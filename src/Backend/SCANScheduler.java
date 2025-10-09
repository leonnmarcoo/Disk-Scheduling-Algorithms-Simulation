package Backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SCANScheduler implements DiskScheduler {
    
    @Override
    public String getAlgorithmName() {
        return "SCAN (Elevator Algorithm)";
    }
    
    @Override
    public SchedulingResult schedule(List<DiskRequest> requests, int initialHeadPosition, int diskSize) {
        List<Integer> seekSequence = new ArrayList<>();
        List<Integer> seekDistances = new ArrayList<>();
        
        // Separate requests into left and right of current position
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        
        for (DiskRequest request : requests) {
            if (request.getTrackNumber() < initialHeadPosition) {
                left.add(request.getTrackNumber());
            } else {
                right.add(request.getTrackNumber());
            }
        }
        
        // Sort both lists
        Collections.sort(left, Collections.reverseOrder()); // Descending for left
        Collections.sort(right); // Ascending for right
        
        seekSequence.add(initialHeadPosition);
        int currentPosition = initialHeadPosition;
        int totalSeekTime = 0;
        
        // First, service requests to the right (towards higher track numbers)
        for (int track : right) {
            seekSequence.add(track);
            int seekDistance = Math.abs(track - currentPosition);
            seekDistances.add(seekDistance);
            totalSeekTime += seekDistance;
            currentPosition = track;
        }
        
        // If there were requests on the right, go to the end
        if (!right.isEmpty() && !left.isEmpty()) {
            seekSequence.add(diskSize - 1);
            int seekDistance = Math.abs((diskSize - 1) - currentPosition);
            seekDistances.add(seekDistance);
            totalSeekTime += seekDistance;
            currentPosition = diskSize - 1;
        }
        
        // Then service requests to the left
        for (int track : left) {
            seekSequence.add(track);
            int seekDistance = Math.abs(track - currentPosition);
            seekDistances.add(seekDistance);
            totalSeekTime += seekDistance;
            currentPosition = track;
        }
        
        double averageSeekTime = requests.isEmpty() ? 0 : (double) totalSeekTime / requests.size();
        
        return new SchedulingResult(seekSequence, totalSeekTime, averageSeekTime, seekDistances);
    }
}
