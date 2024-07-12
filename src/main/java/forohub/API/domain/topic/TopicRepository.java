package forohub.API.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    Page<Topic> findByActiveTrue(Pageable pagination);
    Optional<Topic> findByTitle(String title);
    Optional<Topic> findByMessage(String message);
}
