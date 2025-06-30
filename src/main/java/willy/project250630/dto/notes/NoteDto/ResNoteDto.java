package willy.project250630.dto.notes.NoteDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResNoteDto {
    private Long id;
    private String title;
    private String content;
}
