package willy.project250630.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import willy.project250630.dto.notes.NoteDto.ReqNoteDto;
import willy.project250630.model.tnote.NoteSpecifications;
import willy.project250630.model.tnote.TNote;
import willy.project250630.repository.NoteRepository;
import willy.project250630.util.ResponseUtil;

import java.util.List;

@Slf4j
@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepo;

    public ResponseEntity<Object> getAllNotes(){
        List<TNote> tNotes = noteRepo.findAll();
        return ResponseUtil.okResponse("List of notes", tNotes);
    }

    public ResponseEntity<Object> searchNotes(String search){
        Specification<TNote> spec = Specification.where(NoteSpecifications.hasTitleLike(search))
                .or(NoteSpecifications.hasTitleLike(search));

        List<TNote> tNotes = noteRepo.findAll(spec);
        if(tNotes.isEmpty()){
            return ResponseUtil.notFoundDataResponse("Notes not found");
        }
        return ResponseUtil.okResponse("List of notes", tNotes);
    }

    public ResponseEntity<Object> createNote(ReqNoteDto note){
        TNote tNote = new TNote();
        tNote.setContent(note.getContent());
        tNote.setTitle(note.getTitle());
        noteRepo.save(tNote);
        return ResponseUtil.createdResponse("Note Created Successfully", tNote);
    }
}
