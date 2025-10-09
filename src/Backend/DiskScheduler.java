package Backend;

import java.util.List;

public interface DiskScheduler {
    SchedulingResult schedule(List<DiskRequest> requests, int initialHeadPosition, int diskSize);
    String getAlgorithmName();
}
