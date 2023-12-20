package lt.techin.lectureone.external;


import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OpenLibraryClient {

    //TODO http client <- a thing that would call our openlibrary api

    HttpClient httpClient = HttpClient.newHttpClient();
    String baseURI = "openlibrary.org";
    String searchEndpoint = "/search.json";

    public String lookupBookByName(String bookName) throws IOException, InterruptedException { //TODO use in service + create an endpoint

        System.out.println("calling openlibrary api..");
        HttpRequest httpRequest = HttpRequest.newBuilder()

                .uri(new DefaultUriBuilderFactory().builder()
                        .scheme("https")
                        .host((baseURI))
                        .path(searchEndpoint)
                        .queryParam("q", bookName)
                        .build()
                )
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body(); //TODO map body to the model we created
    }

}
