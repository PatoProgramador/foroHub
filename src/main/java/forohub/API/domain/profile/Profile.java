package forohub.API.domain.profile;

import forohub.API.domain.answer.Answer;
import forohub.API.domain.course.Course;
import forohub.API.domain.profile.DTOS.DtoUpdateProfile;
import forohub.API.domain.topic.DTOS.DtoUpdateTopic;
import forohub.API.domain.topic.Topic;
import forohub.API.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "profiles")
@Entity(name = "Profile")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private User user;

    @OneToMany(mappedBy = "profile",  cascade = CascadeType.ALL)
    private List<Topic> topicList;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL)
    private List<Answer> answerList;

    public void updateData(DtoUpdateProfile dtoUpdateProfile) {
        if (dtoUpdateProfile.name() != null) {
            this.name = dtoUpdateProfile.name();
        }
        if (dtoUpdateProfile.email() != null) {
            this.email = dtoUpdateProfile.email();
        }
    }

    public void deactivateProfile() {
        this.active = false;
    }
}
