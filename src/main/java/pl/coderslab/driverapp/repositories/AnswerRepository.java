package pl.coderslab.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.driverapp.models.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
