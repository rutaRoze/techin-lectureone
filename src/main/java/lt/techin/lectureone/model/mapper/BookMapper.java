package lt.techin.lectureone.model.mapper;

import lombok.experimental.UtilityClass;
import lt.techin.lectureone.external.model.BookInfo;
import lt.techin.lectureone.external.model.BookSearchResponse;
import lt.techin.lectureone.model.response.BookResponse;

@UtilityClass
public class BookMapper {

    public static BookResponse map(BookSearchResponse bookSearchResponse) {

        BookInfo bookInfo = bookSearchResponse.getDocs().iterator().next();

       BookResponse.BookResponseBuilder builder = BookResponse.builder()
                .title(bookInfo.getTitle())
                .publishYear(bookInfo.getFirstPublishYear());

               if(bookInfo.getAuthorName() != null && bookInfo.getAuthorName().isEmpty()) {
                   builder.author(bookInfo.getAuthorName().getFirst());
               }

        if(bookInfo.getAuthorKey() != null && bookInfo.getAuthorKey().isEmpty()) {
            builder.authorId(bookInfo.getAuthorKey().getFirst());
        }

        if(bookInfo.getTags() != null && bookInfo.getTags().isEmpty()) {
            builder.tags(bookInfo.getTags());
        }

       return builder.build();
    }

}