package lt.techin.lectureone.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReactionByAuthorResponse {

    private List<String> like;

    private List<String> dislike;

    private List<String> usersList;
}
