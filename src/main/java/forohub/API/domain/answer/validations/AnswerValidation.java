package forohub.API.domain.answer.validations;

import forohub.API.domain.answer.DTOS.DtoCreateAnswer;

public interface AnswerValidation {
    void validate(DtoCreateAnswer dtoCreateAnswer);
}
