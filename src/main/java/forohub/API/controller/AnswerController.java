package forohub.API.controller;

import forohub.API.domain.answer.CreateAnswerService;
import forohub.API.domain.answer.DTOS.DtoCreateAnswer;
import forohub.API.domain.answer.DTOS.DtoListAnswers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas", description = "Operaciones CRUD en la entidad respuestas")
public class AnswerController {

    @Autowired
    private CreateAnswerService answerService;

    @PostMapping
    @Operation(summary = "Registra una respuesta en la base de datos")
    public ResponseEntity<DtoListAnswers> createAnswer(@RequestBody @Valid DtoCreateAnswer dtoCreateAnswer, UriComponentsBuilder uriComponentsBuilder) {
        DtoListAnswers dtoListAnswers = answerService.create(dtoCreateAnswer);

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(dtoListAnswers.id()).toUri();
        return ResponseEntity.created(url).body(dtoListAnswers);
    }

}
