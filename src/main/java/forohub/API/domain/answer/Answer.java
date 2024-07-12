package forohub.API.domain.answer;

import forohub.API.domain.answer.DTOS.DtoUpdateAnswer;
import forohub.API.domain.profile.Profile;
import forohub.API.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "answers")
@Entity(name = "Answer")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime creation_date;
    private Boolean solution;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_autor")
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_topic")
    private Topic topic;

    public void updateAnsw(DtoUpdateAnswer dtoUpdateAnswer) {
        if (dtoUpdateAnswer.solution()) {
            this.solution = dtoUpdateAnswer.solution();
        }
        if (dtoUpdateAnswer.message() != null) {
            this.message = dtoUpdateAnswer.message();
        }
    }

    public void deactivate() {
        this.active = false;
    }
}
