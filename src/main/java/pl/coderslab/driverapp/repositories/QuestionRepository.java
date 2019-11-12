package pl.coderslab.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.driverapp.models.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
