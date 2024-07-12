package forohub.API.domain.topic.DTOS;

import jakarta.validation.constraints.NotNull;

public record DtoRegisterTopic(
        @NotNull
       String title,
       @NotNull
       String message,
       @NotNull
       Long idAutor,
       @NotNull
       Long idCourse
) { }
