package forohub.API.controller;

import forohub.API.domain.topic.CreateTopicService;
import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import forohub.API.domain.topic.DTOS.DtoTopicList;
import forohub.API.domain.topic.DTOS.DtoUpdateTopic;
import forohub.API.domain.topic.Topic;
import forohub.API.domain.topic.TopicRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topic", description = "Operaciones CRUD en la entidad topic")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CreateTopicService createTopicService;

    @GetMapping
    @Operation(summary = "Obtiene todos los topics")
    public ResponseEntity<Page<DtoTopicList>> listTopics(@PageableDefault(size = 5) Pageable pagination)  {
        return ResponseEntity.ok(topicRepository.findByActiveTrue(pagination).map(DtoTopicList::new));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene el registro de un topic con ID")
    public ResponseEntity<DtoTopicList> findTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);

        return ResponseEntity.ok(new DtoTopicList(topic));
    }

    @PostMapping
    @Operation(summary = "Registra un nuevo topic en la base de datos")
    public ResponseEntity<DtoTopicList> create(@RequestBody @Valid DtoRegisterTopic dtoRegisterTopic,  UriComponentsBuilder uriComponentsBuilder) {
        DtoTopicList result = createTopicService.create(dtoRegisterTopic);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(result.id()).toUri();
        return  ResponseEntity.created(url).body(result);
    }

    @PutMapping
    @Transactional
    @Operation(summary = "Actualiza los datos de un topic existente")
    public ResponseEntity<DtoTopicList> updateTopic(@RequestBody @Valid DtoUpdateTopic dtoUpdateTopic) {
        Topic topic = topicRepository.getReferenceById(dtoUpdateTopic.id());
        topic.updateData(dtoUpdateTopic);
        return ResponseEntity.ok(new DtoTopicList(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Marca un topic registrado como inactivo")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.deactivateTopic();
        return ResponseEntity.noContent().build();
    }
}
