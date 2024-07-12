package forohub.API.controller;

import forohub.API.domain.answer.Answer;
import forohub.API.domain.answer.AnswerRepository;
import forohub.API.domain.answer.CreateAnswerService;
import forohub.API.domain.answer.DTOS.DtoCreateAnswer;
import forohub.API.domain.answer.DTOS.DtoListAnswers;

import forohub.API.domain.answer.DTOS.DtoUpdateAnswer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Respuestas", description = "Operaciones CRUD en la entidad respuestas")
public class AnswerController {

    @Autowired
    private CreateAnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping
    @Operation(summary = "Obtiene todas las respuestas")
    public ResponseEntity<Page<DtoListAnswers>> listAnswers(@PageableDefault(size = 5) Pageable pageable){
        return ResponseEntity.ok(answerRepository.findByActiveTrue(pageable).map(DtoListAnswers::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el registro de una respuesta por id")
    public ResponseEntity<DtoListAnswers> findAnwerById(@PathVariable Long id) {
        Answer answer = answerRepository.getReferenceById(id);
        return ResponseEntity.ok(new DtoListAnswers(answer));
    }

    @PostMapping
    @Operation(summary = "Registra una respuesta en la base de datos")
    public ResponseEntity<DtoListAnswers> createAnswer(@RequestBody @Valid DtoCreateAnswer dtoCreateAnswer, UriComponentsBuilder uriComponentsBuilder) {
        DtoListAnswers dtoListAnswers = answerService.create(dtoCreateAnswer);

        URI url = uriComponentsBuilder.path("/respuestas/{id}").buildAndExpand(dtoListAnswers.id()).toUri();
        return ResponseEntity.created(url).body(dtoListAnswers);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de una respuesta")
    public ResponseEntity<DtoListAnswers> updateAnswer(@RequestBody @Valid DtoUpdateAnswer dtoUpdateAnswer) {
        Answer answer = answerRepository.getReferenceById(dtoUpdateAnswer.id());
        answer.updateAnsw(dtoUpdateAnswer);
        return ResponseEntity.ok(new DtoListAnswers(answer));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Marca una respuesta como inactiva")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        Answer answer = answerRepository.getReferenceById(id);
        answer.deactivate();
        return ResponseEntity.noContent().build();
    }

}
