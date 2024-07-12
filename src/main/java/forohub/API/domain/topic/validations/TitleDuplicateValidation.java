package forohub.API.domain.topic.validations;

import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import forohub.API.domain.topic.Topic;
import forohub.API.domain.topic.TopicRepository;
import forohub.API.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class TitleDuplicateValidation implements TopicValidator{
    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(DtoRegisterTopic dtoRegisterTopic) {
        Optional<Topic> topic = topicRepository.findByTitle(dtoRegisterTopic.title());

        if (topic.isPresent()) {
            throw new IntegrityValidation("Ya existe un topico con este mismo titulo");
        }
    }
}
