package lt.techin.lectureone.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder (setterPrefix = "with")
@ToString
@Getter
public class User {

    public int id;

    @Setter
    public String name;
    public int age;

}
