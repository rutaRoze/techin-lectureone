package lt.techin.lectureone.persistence.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lt.techin.lectureone.model.request.ReactionAction;

import java.util.Locale;

@Getter
@Entity
@NoArgsConstructor
public class ReactionRecord {

    public ReactionRecord(String uuid, String olid, ReactionAction reactionAction) {
        this.id = uuid.toLowerCase(Locale.ROOT).strip().concat(":")
                .concat(olid.toLowerCase(Locale.ROOT).strip());
        this.uuid = uuid;
        this.olid = olid;
        this.reactionAction = reactionAction.name();
    }

    @Id
    private String id;

    private String uuid;

    private String olid;

    private String reactionAction;

}
