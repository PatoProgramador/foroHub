package forohub.API.domain.topic.validations;

import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class MessageLengthValidation implements TopicValidator {

    public void validate(DtoRegisterTopic dtoRegisterTopic) {
        if (dtoRegisterTopic.message().length() < 10) {
            throw new ValidationException("El mensaje es demasiado corto, recuerda que debe ser descriptivo");
        }
        if (dtoRegisterTopic.message().length() > 99) {
            throw new ValidationException("El mensaje es demasiado largo");
        }
    }
}
