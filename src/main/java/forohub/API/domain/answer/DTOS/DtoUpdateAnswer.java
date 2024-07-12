package forohub.API.domain.answer.DTOS;

import jakarta.validation.constraints.NotNull;

public record DtoUpdateAnswer(
        @NotNull
        Long id,
        String message,
        Boolean solution
) {}
