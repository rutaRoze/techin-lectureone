package lt.techin.lectureone.external.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SearchResponse {
    List<AuthorInfo> docs;
}