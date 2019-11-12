package pl.coderslab.driverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.driverapp.models.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
