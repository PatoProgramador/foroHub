package forohub.API.domain.answer.validations;

import forohub.API.domain.answer.DTOS.DtoCreateAnswer;
import forohub.API.domain.profile.ProfileRepository;
import forohub.API.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AutorAnswerValidation implements AnswerValidation{

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public void validate(DtoCreateAnswer dtoCreateAnswer) {
        if (profileRepository.findById(dtoCreateAnswer.idAutor()).isEmpty()) {
            throw new IntegrityValidation("No fue encontrado un perfil con este id");
        }
    }
}
