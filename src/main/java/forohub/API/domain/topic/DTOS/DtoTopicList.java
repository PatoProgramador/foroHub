package forohub.API.domain.topic.DTOS;

import forohub.API.domain.topic.Topic;

import java.time.LocalDateTime;

public record DtoTopicList(
        Long id,
        String titulo,
        String mensaje,
        Boolean estado,
        Long idAutor,
        String autor,
        Long idCurso,
        String curso,
        LocalDateTime fechaCreacion
) {
    public DtoTopicList(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getMessage(), topic.getStatus(),topic.getProfile().getId(),
                topic.getProfile().getName(), topic.getCourse().getId(), topic.getCourse().getName(),
                topic.getCreation_date());
    }
}
