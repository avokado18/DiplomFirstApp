package imageWorker;

import java.io.IOException;
import java.util.List;

public interface ImageWorker {
    List<String> detectLabels(String fileName) throws IOException;
}
