package forohub.API.domain.topic.validations;

import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class TitleValidation implements TopicValidator{

    public void validate(DtoRegisterTopic dtoRegisterTopic) {
        if (dtoRegisterTopic.title().length() < 4) {
            throw new ValidationException("El titulo es demasiado corto, recuerda que debe ser descriptivo");
        }
    }
}
