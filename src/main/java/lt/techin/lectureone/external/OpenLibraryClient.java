package lt.techin.lectureone.external;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.external.model.SearchResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class OpenLibraryClient {

    private HttpClient httpClient = HttpClient.newHttpClient();

    private ObjectMapper mapper = JsonMapper.builder()
            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .build();
    private String baseURI = "openlibrary.org";
    private String searchAuthorsEndpoint = "/search/authors.json";
    private String getAuthorsWorks = "/authors/{olid}/works.json";

    public String getAuthorOlid(String author) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new DefaultUriBuilderFactory().builder()
                        .scheme("https")
                        .host(baseURI)
                        .path(searchAuthorsEndpoint)
                        .queryParam("q", author)
                        .build())
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        SearchResponse searchResponse = mapper.readValue(httpResponse.body(), SearchResponse.class);

        if (searchResponse != null && (!searchResponse.getDocs().isEmpty())) {
            return searchResponse.getDocs().getFirst().getKey();
        }

        return "";
    }

    public AuthorWorksResponse getWorks(String olid) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new DefaultUriBuilderFactory().builder()
                        .scheme("https")
                        .host(baseURI)
                        .path(getAuthorsWorks)
                        .build(olid))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        return mapper.readValue(httpResponse.body(), AuthorWorksResponse.class);
    }
}