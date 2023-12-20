package lt.techin.lectureone.model.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class BookResponse {

    private String title;
    private int publishYear;
    private String author;
    private String authorId;
    private List<String> tags;
}
