package lt.techin.lectureone.model.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UUID;

@Getter
@Setter
@ToString
public class RecordReactionRequest {

    @UUID
    @NotBlank
    private String uuid;

    @NotBlank
    @Pattern(regexp = "OL[\\d]+A")
    private String olid;

    @NotNull
    private ReactionAction action;

}
