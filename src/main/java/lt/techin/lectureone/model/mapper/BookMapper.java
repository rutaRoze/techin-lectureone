package lt.techin.lectureone.model.mapper;

import lombok.experimental.UtilityClass;
import lt.techin.lectureone.external.model.BookInfo;
import lt.techin.lectureone.external.model.BookSearchResponse;
import lt.techin.lectureone.model.response.BookResponse;

@UtilityClass
public class BookMapper {

    public static BookResponse map(BookSearchResponse bookSearchResponse) {

        BookInfo bookInfo = bookSearchResponse.getDocs().iterator().next();

        return BookResponse.builder()
                .title(bookInfo.getTitle())
                .publishYear(bookInfo.getFirstPublishYear())
                .author(bookInfo.getAuthorName().getFirst())
                .authorId(bookInfo.getAuthorKey().getFirst())
                .tags(bookInfo.getTags())
                .build();
    }



}