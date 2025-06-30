package willy.project250630.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import willy.project250630.dto.notes.NoteDto.ReqNoteDto;
import willy.project250630.service.NoteService;

@RestController
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping
    public ResponseEntity<Object> getAllNotes(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return noteService.getAllNotes();
        }
        return noteService.searchNotes(search);
    }

    @PostMapping
    public ResponseEntity<Object> createNote(@RequestBody ReqNoteDto note) {
        return noteService.createNote(note);
    }
}
