package imageWorker;

import com.google.cloud.vision.spi.v1.ImageAnnotatorClient;
import com.google.cloud.vision.v1.*;
import com.google.protobuf.ByteString;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImageWorker {
    public static List<String> detectLabels() throws IOException {
        List<String> result = new ArrayList<>();
        // Instantiates a client
        ImageAnnotatorClient vision = ImageAnnotatorClient.create();

        // The path to the image file to annotate
        String fileName = "src/main/resources/people2.jpg";

        // Reads the image file into memory
        Path path = Paths.get(fileName);
        byte[] data = Files.readAllBytes(path);
        ByteString imgBytes = ByteString.copyFrom(data);

        // Builds the image annotation request
        List<AnnotateImageRequest> requests = new ArrayList<>();
        Image img = Image.newBuilder().setContent(imgBytes).build();
        Feature feat = Feature.newBuilder().setType(Feature.Type.LABEL_DETECTION).build();
        AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                .addFeatures(feat)
                .setImage(img)
                .build();
        requests.add(request);

        // Performs label detection on the image file
        BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
        List<AnnotateImageResponse> responses = response.getResponsesList();

        for (AnnotateImageResponse res : responses) {
            if (res.hasError()) {
                System.out.printf("Error: %s\n", res.getError().getMessage());
                return null;
            }

            for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
                result.add(annotation.getDescription());
            }
        }
        return result;
    }
}
