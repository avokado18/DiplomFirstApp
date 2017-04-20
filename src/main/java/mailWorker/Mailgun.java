package mailWorker;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Mailgun {

    public static Response sendSimpleMessage(String message) {
        Client client = ClientBuilder.newClient();
        client.register(HttpAuthenticationFeature.basic(
                "api",
                "key-6e0ff35fd2e3c57472b42cfe65736abe"
        ));

        WebTarget mgRoot = client.target("https://api.mailgun.net/v3");

        Form reqData = new Form();
        reqData.param("from", "this is me <postmaster@sandboxbd0714c1aa0c4c4ca31e276292201876.mailgun.org>");
        reqData.param("to", "proskuryakova1996@gmail.com");
        reqData.param("subject", "Mailgun");
        reqData.param("text", message);

        return mgRoot
                .path("/{domain}/messages")
                .resolveTemplate("domain", "sandboxbd0714c1aa0c4c4ca31e276292201876.mailgun.org")
                .request(MediaType.APPLICATION_FORM_URLENCODED)
                .buildPost(Entity.entity(reqData, MediaType.APPLICATION_FORM_URLENCODED))
                .invoke(Response.class);
    }
}
