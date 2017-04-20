import imageWorker.ImageWorker;
import mailWorker.MailSender;
import mailWorker.Mailgun;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> tags = ImageWorker.detectLabels();
            System.out.println(tags);
        } catch (Exception ex){
            ex.printStackTrace();
        }

        MailSender mailSender = new MailSender();
        mailSender.send();


    }
}
