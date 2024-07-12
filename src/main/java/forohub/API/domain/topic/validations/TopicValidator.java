package forohub.API.domain.topic.validations;

import forohub.API.domain.topic.DTOS.DtoRegisterTopic;

public interface TopicValidator {
    void validate(DtoRegisterTopic dtoRegisterTopic);
}
