package lt.techin.lectureone.service;

import lombok.RequiredArgsConstructor;
import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.model.mapper.BookMapper;
import lt.techin.lectureone.model.response.BookResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final OpenLibraryClient openLibraryClient;

    public BookResponse getAuthorWorks(String author) {
        String olid = openLibraryClient.getAuthorOlid(author);
        AuthorWorksResponse authorWorksResponse = openLibraryClient.geWorks(olid);

        return BookMapper.map(authorWorksResponse, author);
    }
}
