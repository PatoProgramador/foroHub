package forohub.API.domain.topic.validations;

import forohub.API.domain.course.CourseRepository;
import forohub.API.domain.topic.DTOS.DtoRegisterTopic;
import forohub.API.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseValidation implements TopicValidator{
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void validate(DtoRegisterTopic dtoRegisterTopic) {
        if (courseRepository.findById(dtoRegisterTopic.idCourse()).isEmpty()) {
            throw new IntegrityValidation("No fue encontrado un curso con este id");
        }
    }
}
