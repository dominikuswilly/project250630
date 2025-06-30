package willy.project250630;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import willy.project250630.controller.publicapi.NoteController;
import willy.project250630.dto.notes.NoteDto.ReqNoteDto;
import willy.project250630.model.tnote.TNote;
import willy.project250630.service.NoteService;
import willy.project250630.util.ResponseUtil;

import java.util.Map;


@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCreateNote() throws Exception {
        // Prepare the request DTO
        ReqNoteDto reqDto = new ReqNoteDto("a", "a");

        // Prepare the mocked response from service
        TNote tNote = new TNote(1L, "a", "a");

        // Mock behavior
        Mockito.when(noteService.createNote(Mockito.any(ReqNoteDto.class)))
                .thenReturn(ResponseUtil.createdResponse("Note Created Successfully", tNote));

        // Perform POST request and validate response
        mockMvc.perform(post("/v1/public/notes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Note Created Successfully"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("a"))
                .andExpect(jsonPath("$.data.content").value("a"));
    }

    @Test
    public void testViewNote() throws Exception {
        TNote tNote = new TNote(1L, "a", "a");

        Mockito.when(noteService.getAllNotes())
                .thenReturn(ResponseUtil.okResponse("List of notes", tNote));

        mockMvc.perform(get("/v1/public/notes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("List of notes"))
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.title").value("a"))
                .andExpect(jsonPath("$.data.content").value("a"));
    }

    @Test
    public void testViewNoteNotFound() throws Exception {
        TNote tNote = new TNote(1L, "a", "a");

        Mockito.when(noteService.getAllNotes())
                .thenReturn(ResponseUtil.notFoundDataResponse("No Note on database"));

        mockMvc.perform(get("/v1/public/notes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No Note on database"));
    }
}
