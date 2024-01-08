package lt.techin.lectureone.model.mapper;

import lombok.experimental.UtilityClass;
import lt.techin.lectureone.external.model.AuthorWorksResponse;
import lt.techin.lectureone.external.model.BookInfo;
import lt.techin.lectureone.external.model.BookSearchResponse;
import lt.techin.lectureone.model.response.Book;
import lt.techin.lectureone.model.response.BookResponse;
import lt.techin.lectureone.model.response.WorksResponse;

@UtilityClass
public class BookMapper {

//    public static BookResponse map(BookSearchResponse bookSearchResponse) {
//        //todo get first result and map it to our book response model
//
//        BookInfo bookInfo = bookSearchResponse.getDocs().iterator().next();
//
//        return BookResponse.builder()
//                .title(bookInfo.getTitle())
//                .publishYear(bookInfo.getFirstPublishYear())
//                .author(bookInfo.getAuthorName().get(0))
//                .authorId(bookInfo.getAuthorKey().get(0))
//                .tags(bookInfo.getTags())
//                .build();
//    }


    public static WorksResponse map(AuthorWorksResponse authorWorksResponse, String author) {


        return WorksResponse.builder()
                .author(author)
                .books(authorWorksResponse.getEntries().stream()
                        .map(BookMapper::mapBook)
                        .colle)
                .build();
    }

    public static Book mapBook (AuthorWorksResponse.WorkEntry workEntry) {
        return null;
    }
}