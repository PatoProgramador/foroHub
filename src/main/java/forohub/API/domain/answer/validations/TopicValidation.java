package forohub.API.domain.answer.validations;

import forohub.API.domain.answer.DTOS.DtoCreateAnswer;
import forohub.API.domain.topic.TopicRepository;
import forohub.API.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidation implements AnswerValidation{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public void validate(DtoCreateAnswer dtoCreateAnswer) {
        if (topicRepository.findById(dtoCreateAnswer.idTopic()).isEmpty()) {
            throw new IntegrityValidation("No fue encontrado un topico con este id");
        }
    }
}
