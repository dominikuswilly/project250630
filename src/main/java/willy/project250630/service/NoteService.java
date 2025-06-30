package willy.project250630.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import willy.project250630.dto.notes.NoteDto.ReqNoteDto;
import willy.project250630.dto.notes.NoteDto.ResNoteDto;
import willy.project250630.model.tnote.NoteSpecifications;
import willy.project250630.model.tnote.TNote;
import willy.project250630.repository.tnote.NoteIdAndTitle;
import willy.project250630.repository.tnote.NoteRepository;
import willy.project250630.util.ResponseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepo;

    public ResponseEntity<Object> getAllNotes(){
        try {
            List<NoteIdAndTitle> tNotes = noteRepo.findAllProjectedBy();
            if(tNotes.isEmpty()){
                return ResponseUtil.okResponse("No Note on database", tNotes);
            }
            return ResponseUtil.okResponse("List of notes", tNotes);
        }catch(Exception e){
            return ResponseUtil.internalServerErrorResponse(e.getMessage());
        }
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

    public ResponseEntity<Object> getDetailNote(Long id){
        Optional<TNote> tNoteOptional = noteRepo.getTNoteById(id);
        if(!tNoteOptional.isPresent()){
            return ResponseUtil.notFoundDataResponse("Note not found");
        }

        ResNoteDto dto = new ResNoteDto();
        dto.setId(tNoteOptional.get().getId());
        dto.setContent(tNoteOptional.get().getContent());
        dto.setTitle(tNoteOptional.get().getTitle());


        return ResponseUtil.okResponse("message", dto);
    }

    public ResponseEntity<Object> deleteNote(Long id){
        Optional<TNote> tNoteOptional = noteRepo.getTNoteById(id);
        if(!tNoteOptional.isPresent()){
            return ResponseUtil.notFoundDataResponse("Note not found to be deleted");
        }

        noteRepo.deleteById(id);
        return ResponseUtil.okResponse("Note Deleted Successfully", tNoteOptional.get());
    }
}
