package forohub.API.domain.profile.DTOS;

import jakarta.validation.constraints.NotNull;

public record DtoUpdateProfile(
        @NotNull
        Long id,
        String name,
        String email
) { }
