package forohub.API.controller;

import forohub.API.domain.topic.CreateTopicService;
import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import forohub.API.domain.topic.DTOS.DtoTopicList;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicController {

    @Autowired
    private CreateTopicService createTopicService;

    @PostMapping
    public ResponseEntity<DtoTopicList> create(@RequestBody @Valid DtoRegisterTopic dtoRegisterTopic) {
        DtoTopicList result = createTopicService.create(dtoRegisterTopic);

        return  ResponseEntity.ok(result);
    }
}
