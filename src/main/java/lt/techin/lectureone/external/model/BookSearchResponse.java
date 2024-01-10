package lt.techin.lectureone.external.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

@Getter
@Setter
public class BookSearchResponse {

    Collection<BookInfo> docs;
}