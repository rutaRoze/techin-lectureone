package lt.techin.lectureone.service;

import lt.techin.lectureone.external.OpenLibraryClient;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.model.response.Book;
import lt.techin.lectureone.model.response.BookResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

class BookServiceTest {

    BookService bookService;

    @Mock
    OpenLibraryClient openLibraryClientMock;

    @Test
    void getAuthorWorks() {

        Mockito.when(openLibraryClientMock.getAuthorOlid(anyString())).thenReturn("OLID");
        Mockito.when(openLibraryClientMock.getAuthorOlid(eq("OLID"))).thenReturn(new AuthorWorksResponse());


//        BookResponse actual = bookService.getAuthorWorks("testValue");

        Book.builder()
                .title("Title")
                .descritpion("Description")
                .publishDate("Date")
                .build();

        Assertions.assertEquals("Description", actual.getDescription());
        Assertions.assertEquals("Date", actual.getDate());
        Assertions.assertEquals("Title", actual.getTitle());

        Assertions.assertTrue();

    }

}