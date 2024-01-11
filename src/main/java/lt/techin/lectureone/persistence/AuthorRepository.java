package lt.techin.lectureone.persistence;

import lt.techin.lectureone.persistence.modal.AuthorRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorRecord, String> {


}
