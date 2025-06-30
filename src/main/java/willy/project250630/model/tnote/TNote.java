package willy.project250630.model.tnote;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "t_note")
@Data
public class TNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String content;

    public TNote(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public TNote() {

    }
}
