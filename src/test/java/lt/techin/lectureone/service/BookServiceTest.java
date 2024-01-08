package lt.techin.lectureone.service;

import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.model.response.Book;
import lt.techin.lectureone.model.response.BookResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class BookServiceTest {

    BookService bookService;

    @Mock
    OpenLibraryClient openLibraryClientMock;

    @Test
    void getAuthorWorks() throws IOException, InterruptedException {

        Mockito.when(openLibraryClientMock.getAuthorOlid(anyString())).thenReturn("OLID");
        AuthorWorksResponse authorWorksResponse = new AuthorWorksResponse();
        Mockito.when(openLibraryClientMock.getAuthorOlid(eq("OLID"))).thenReturn(authorWorksResponse);


        BookResponse actual = bookService.getAuthorWorks("testValue");

        Book.builder()
                .title("Title")
                .description("Description")
                .publishDate("Date")
                .build();

        Book firstBook = actual.getBooks().getFirst();
        Assertions.assertEquals("Description", firstBook.getDescription());
        Assertions.assertEquals("Date", firstBook.getPublishDate());
        Assertions.assertEquals("Title", firstBook.getTitle());
    }
}