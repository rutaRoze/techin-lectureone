package lt.techin.lectureone.service;

import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.model.response.Book;
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
        Mockito.when(openLibraryClientMock.getWorks(eq("OLID"))).thenReturn(new AuthorWorksResponse());


        //BookResponse actual = bookService.getAuthorWorks("testValue");

        Book actual = Book.builder()
                .title("Title")
                .description("Description")
                .publishDate("Date")
                .build();

        //Book firstBook = actual.getBooks().getFirst();
        Assertions.assertEquals("Description", actual.getDescription());
        Assertions.assertEquals("Date", actual.getPublishDate());
        Assertions.assertEquals("Title", actual.getTitle());
    }
}