package lt.techin.lectureone.persistence.modal;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthorRecord {

    @Id
    private String authorName;

    private String olid;
}
