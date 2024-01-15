package lt.techin.lectureone.persistence;

import lt.techin.lectureone.persistence.modal.ReactionRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReactionRepository extends JpaRepository<ReactionRecord, String> {

    public List<ReactionRecord> findByUuid(String uuid);

    public List<ReactionRecord> findByOlid(String uuid);

}
