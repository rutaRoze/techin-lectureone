package lt.techin.lectureone.external;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lt.techin.lectureone.external.model.BookSearchResponse;
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

    private ObjectMapper mapper = JsonMapper.builder()
            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            .build();
    private HttpClient httpClient = HttpClient.newHttpClient();
    private String baseURI = "openlibrary.org";
    private String searchEndpoint = "/search.json";

    public BookSearchResponse lookupBookByName(String bookName) throws IOException, InterruptedException { //TODO use in service + create an endpoint

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


        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString()); //TODO map body to the model we created

        BookSearchResponse response = mapper.readValue(httpResponse.body(), BookSearchResponse.class);

        return response;
    }

}
