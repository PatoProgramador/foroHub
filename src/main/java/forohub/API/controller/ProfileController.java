package forohub.API.controller;

import forohub.API.domain.profile.DTOS.DtoListProfile;
import forohub.API.domain.profile.DTOS.DtoRegisterProfile;
import forohub.API.domain.profile.DTOS.DtoUpdateProfile;
import forohub.API.domain.profile.Profile;
import forohub.API.domain.profile.ProfileRepository;
import forohub.API.domain.topic.Topic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Usuario", description = "Operaciones CRUD en la entidad usuario-perfil")
public class ProfileController {

    @Autowired
    private ProfileRepository profileRepository;

    @GetMapping
    @Operation(summary = "Obtiene todos los usuarios")
    public ResponseEntity<Page<DtoListProfile>> ListProfiles(@PageableDefault(size= 5) Pageable pageable) {
        return ResponseEntity.ok(profileRepository.findByActiveTrue(pageable).map(this::conversorToDTO));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el registro de un usuario por ID")
    public ResponseEntity<DtoListProfile> findProfileById(@PathVariable Long id) {
        Profile profile = profileRepository.getReferenceById(id);

        return ResponseEntity.ok(conversorToDTO(profile));
    }

    @PostMapping
    @Operation(summary = "Registra un usuario en la base de datos")
    public ResponseEntity<DtoListProfile> createProfile(@RequestBody @Valid DtoRegisterProfile dtoRegisterProfile, UriComponentsBuilder uriComponentsBuilder) {
        Profile profile = new Profile(null, dtoRegisterProfile.name(), dtoRegisterProfile.email(), true, null, null, null);
        profileRepository.save(profile);
        DtoListProfile dtoListProfile = conversorToDTO(profile);

        URI url = uriComponentsBuilder.path("/usuario/{id}").buildAndExpand(profile.getId()).toUri();
        return ResponseEntity.created(url).body(dtoListProfile);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un usuario")
    public ResponseEntity<DtoListProfile> updateProfile(@RequestBody @Valid DtoUpdateProfile dtoUpdateProfile) {
        Profile profile = profileRepository.getReferenceById(dtoUpdateProfile.id());
        profile.updateData(dtoUpdateProfile);
        DtoListProfile dtoListProfile = conversorToDTO(profile);
        return ResponseEntity.ok(dtoListProfile);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Marca al ususario como inactivo")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        Profile profile = profileRepository.getReferenceById(id);
        profile.deactivateProfile();
        return ResponseEntity.noContent().build();
    }

    private DtoListProfile conversorToDTO(Profile profile) {
        if (profile.getTopicList().isEmpty()) {
            return new DtoListProfile(profile.getId(), profile.getName(), profile.getEmail(), null);
        } else {
            return new DtoListProfile(profile.getId(), profile.getName(), profile.getEmail(), profile.getTopicList().stream().map(Topic::getTitle).collect(Collectors.toList()));
        }
    }
}
