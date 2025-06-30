package willy.project250630.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import willy.project250630.model.tnote.TNote;

@Repository
public interface NoteRepository extends JpaRepository<TNote, Integer>, JpaSpecificationExecutor<TNote> {

}
