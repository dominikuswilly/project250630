package willy.project250630.dto.notes.NoteDto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqNoteDto {
    private String title;
    private String content;
}
