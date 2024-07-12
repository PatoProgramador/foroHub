package forohub.API.controller;

import forohub.API.domain.topic.CreateTopicService;
import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import forohub.API.domain.topic.DTOS.DtoTopicList;
import forohub.API.domain.topic.Topic;
import forohub.API.domain.topic.TopicRepository;
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
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CreateTopicService createTopicService;

    @GetMapping
    public ResponseEntity<Page<DtoTopicList>> listTopics(@PageableDefault(size = 5) Pageable pagination)  {
        return ResponseEntity.ok(topicRepository.findAll(pagination).map(DtoTopicList::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoTopicList> findTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);

        return ResponseEntity.ok(new DtoTopicList(topic));
    }

    @PostMapping
    public ResponseEntity<DtoTopicList> create(@RequestBody @Valid DtoRegisterTopic dtoRegisterTopic,  UriComponentsBuilder uriComponentsBuilder) {
        DtoTopicList result = createTopicService.create(dtoRegisterTopic);

        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(result.id()).toUri();
        return  ResponseEntity.created(url).body(result);
    }
}
