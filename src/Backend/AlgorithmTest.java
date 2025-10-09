package Backend;

import java.util.ArrayList;
import java.util.List;

public class AlgorithmTest {
    
    public static void main(String[] args) {
        System.out.println("=== Disk Scheduling Algorithms Test ===\n");
        
        // Test configuration
        int initialHead = 53;
        int diskSize = 200;
        
        // Create test requests - classic example
        List<DiskRequest> requests = new ArrayList<>();
        int[] requestTracks = {98, 183, 37, 122, 14, 124, 65, 67};
        
        for (int i = 0; i < requestTracks.length; i++) {
            requests.add(new DiskRequest(requestTracks[i], i));
        }
        
        System.out.println("Test Configuration:");
        System.out.println("Initial Head Position: " + initialHead);
        System.out.println("Disk Size: " + diskSize);
        System.out.print("Request Queue: ");
        for (int track : requestTracks) {
            System.out.print(track + " ");
        }
        System.out.println("\n");
        
        // Test all algorithms
        DiskScheduler[] schedulers = {
            new FCFSScheduler(),
            new SSTFScheduler(),
            new SCANScheduler(),
            new CSCANScheduler()
        };
        
        for (DiskScheduler scheduler : schedulers) {
            testAlgorithm(scheduler, new ArrayList<>(requests), initialHead, diskSize);
        }
        
        System.out.println("\n=== All Tests Completed Successfully ===");
    }
    
    private static void testAlgorithm(DiskScheduler scheduler, List<DiskRequest> requests, 
                                     int initialHead, int diskSize) {
        System.out.println("----------------------------------------");
        System.out.println("Algorithm: " + scheduler.getAlgorithmName());
        System.out.println("----------------------------------------");
        
        SchedulingResult result = scheduler.schedule(requests, initialHead, diskSize);
        
        System.out.print("Seek Sequence: ");
        for (int track : result.getSeekSequence()) {
            System.out.print(track + " ");
        }
        System.out.println();
        
        System.out.println("\nSeek Details:");
        List<Integer> sequence = result.getSeekSequence();
        List<Integer> distances = result.getSeekDistances();
        
        for (int i = 0; i < distances.size(); i++) {
            System.out.printf("  %3d → %3d : %3d tracks\n", 
                sequence.get(i), sequence.get(i + 1), distances.get(i));
        }
        
        System.out.println("\nPerformance Metrics:");
        System.out.println("  Total Seek Time: " + result.getTotalSeekTime() + " tracks");
        System.out.printf("  Average Seek Time: %.2f tracks\n", result.getAverageSeekTime());
        System.out.println();
    }
}
