package lt.techin.lectureone.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class Book {

    String openLibraryKey;
    String title;
    String publishDate;
    String description;
    private List<String> tags;
}
