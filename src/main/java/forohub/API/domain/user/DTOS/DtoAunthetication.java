package forohub.API.domain.user.DTOS;

import jakarta.validation.constraints.NotNull;

public record DtoAunthetication(
        @NotNull
        String username,
        @NotNull
        String password
) {}
