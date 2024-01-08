package lt.techin.lectureone.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class WorksResponse {

    private String author;
    private List<Book> books;
}
