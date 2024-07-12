package forohub.API.domain.profile.DTOS;

import jakarta.validation.constraints.NotNull;

public record DtoRegisterProfile(
        @NotNull
        String name,
        @NotNull
        String email
) {
}
