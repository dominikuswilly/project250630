package willy.project250630.repository.tnote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import willy.project250630.model.tnote.TNote;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<TNote, Long>, JpaSpecificationExecutor<TNote> {
    List<NoteIdAndTitle> findAllProjectedBy();

    Optional<TNote> getTNoteById(Long id);
}
