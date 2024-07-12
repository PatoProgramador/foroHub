package forohub.API.controller;

import forohub.API.domain.user.DTOS.DtoAunthetication;
import forohub.API.domain.user.DTOS.DtoJwtToken;
import forohub.API.domain.user.User;
import forohub.API.infra.security.TokenService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Tag(name = "Autenticacion", description = "obtiene el token para el usuario asignado que da acceso al resto de endpoint")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid DtoAunthetication dtoAunthetication) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(dtoAunthetication.username(), dtoAunthetication.password());
        Authentication userAuth = authenticationManager.authenticate(authToken);
        String jwtToken = tokenService.generateToken((User) userAuth.getPrincipal());
        return ResponseEntity.ok(new DtoJwtToken(jwtToken));
    }

}
