import imageWorker.ImageWorker;
import imageWorkerImpl.GoogleCVImageWorker;
import mailWorkerImpl.JavaEmailSender;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            ImageWorker imageWorker = new GoogleCVImageWorker();
            List<String> tags = imageWorker.detectLabels("src/main/resources/porshe.jpg");
            if (tags.contains("car")) {
                JavaEmailSender mailSender = new JavaEmailSender();
                mailSender.send("car");
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }




    }
}
