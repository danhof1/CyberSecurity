package Backend.Scans;
import java.nio.file.*;
public class realTimeScan {
    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path directory = Paths.get("/path/to/monitor");
            directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
                WatchKey key = watchService.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context());
                    // Respond to the event here
                }

                key.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
