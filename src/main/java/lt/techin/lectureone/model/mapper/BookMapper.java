package lt.techin.lectureone.model.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import lt.techin.lectureone.external.model.BookInfo;
import lt.techin.lectureone.external.model.BookSearchResponse;
import lt.techin.lectureone.model.response.BookResponse;

//@NoArgsConstructor(access = AccessLevel.PRIVATE) //or
@UtilityClass
public class BookMapper {

    public static BookResponse map (BookSearchResponse bookSearchResponse) {
        //TODO get first result and map it to our book response model

        BookInfo bookInfo = bookSearchResponse.getDocs().iterator().next();

        return BookResponse.builder()
                .title(bookInfo.getTitle())
                .publishYear(bookInfo.getFirstPublishYear())
                .author(bookInfo.getAuthorName().get(0))
                .authorId(bookInfo.getAuthorKey().get(0))
                .tags(bookInfo.getTags())
                .build();
    }
}
