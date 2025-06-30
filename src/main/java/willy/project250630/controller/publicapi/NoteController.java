package willy.project250630.controller.publicapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import willy.project250630.dto.notes.NoteDto.ReqNoteDto;
import willy.project250630.service.NoteService;

@RestController
@RequestMapping("/v1/public/notes")
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping
    @Operation(summary = "Get All Notes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Note Created Successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\"status\":200,\"message\":\"string\",\"data\":[{\"id\":0,\"title\":\"string\"}]}"
                            )
                    )),
            @ApiResponse(responseCode = "404", description = "Note not found"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Note not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\"status\":404,\"message\":\"Notes not found\"}"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\"status\":500,\"message\":\"string\"}"
                            )
                    )
            )
    })
    @Tag(name = "v1", description = "API versi 1 untuk fungsi public")
    public ResponseEntity<Object> getAllNotes(@RequestParam(required = false) String search) {
        if (search == null || search.isEmpty()) {
            return noteService.getAllNotes();
        }
        return noteService.searchNotes(search);
    }

    @PostMapping
    @Operation(summary = "Post Note")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Create Note Successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\"status\":201,\"message\":\"Note Created Successfully\",\"data\":{\"id\":1,\"title\":\"string\",\"content\":\"string\"}}"
                            )
                    )),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\"status\":500,\"message\":\"string\"}"
                            )
                    )
            )
    })
    @Tag(name = "v1", description = "API versi 1 untuk fungsi public")
    public ResponseEntity<Object> createNote(@RequestBody ReqNoteDto note) {
        return noteService.createNote(note);
    }

    @GetMapping("/{id}")
    @Operation(summary = "view detail note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "404", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "500", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}")))
    })
    @Tag(name = "v1", description = "API versi 1 untuk fungsi public")
    public ResponseEntity<Object> getDetailNote(@PathVariable long id) {
        return noteService.getDetailNote(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete single note")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "404", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}"))),
            @ApiResponse(responseCode = "500", description = "", content = @Content(mediaType = "application/json", schema = @Schema(example = "{}")))
    })
    @Tag(name = "v1", description = "API versi 1 untuk fungsi public")
    public ResponseEntity<Object> deleteNote(@PathVariable long id) {
        return noteService.deleteNote(id);
    }

}
