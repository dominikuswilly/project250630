package willy.project250630.dto.notes.NoteDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReqNoteDto {
    private String title;
    private String content;
}
