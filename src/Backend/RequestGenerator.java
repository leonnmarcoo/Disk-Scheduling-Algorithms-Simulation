package Backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RequestGenerator {
    private Random random;
    
    public RequestGenerator() {
        this.random = new Random();
    }
    
    /**
     * Generate random requests
     */
    public List<DiskRequest> generateRandom(int count, int diskSize) {
        List<DiskRequest> requests = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            int track = random.nextInt(diskSize);
            requests.add(new DiskRequest(track, i));
        }
        return requests;
    }
    
    /**
     * Generate clustered requests (grouped around certain areas)
     */
    public List<DiskRequest> generateClustered(int count, int diskSize, int clusterCount) {
        List<DiskRequest> requests = new ArrayList<>();
        int requestsPerCluster = count / clusterCount;
        
        for (int i = 0; i < clusterCount; i++) {
            int clusterCenter = random.nextInt(diskSize);
            int clusterRadius = diskSize / (clusterCount * 4); // 25% of cluster space
            
            for (int j = 0; j < requestsPerCluster; j++) {
                int offset = random.nextInt(clusterRadius * 2) - clusterRadius;
                int track = Math.max(0, Math.min(diskSize - 1, clusterCenter + offset));
                requests.add(new DiskRequest(track, i * requestsPerCluster + j));
            }
        }
        
        // Add remaining requests if count is not evenly divisible
        int remaining = count - (requestsPerCluster * clusterCount);
        for (int i = 0; i < remaining; i++) {
            int track = random.nextInt(diskSize);
            requests.add(new DiskRequest(track, count - remaining + i));
        }
        
        return requests;
    }
    
    /**
     * Generate sequential requests
     */
    public List<DiskRequest> generateSequential(int count, int diskSize, boolean ascending) {
        List<DiskRequest> requests = new ArrayList<>();
        int start = random.nextInt(diskSize / 2);
        int step = Math.max(1, diskSize / count);
        
        for (int i = 0; i < count; i++) {
            int track = start + (ascending ? i * step : -i * step);
            track = Math.max(0, Math.min(diskSize - 1, track));
            requests.add(new DiskRequest(track, i));
        }
        
        return requests;
    }
}
