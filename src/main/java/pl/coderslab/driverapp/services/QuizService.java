package pl.coderslab.driverapp.services;

import org.springframework.stereotype.Service;
import pl.coderslab.driverapp.DTO.QuizDTO;
import pl.coderslab.driverapp.exceptions.EntityNotFoundException;
import pl.coderslab.driverapp.models.Quiz;
import pl.coderslab.driverapp.repositories.QuizRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuizService {

    private QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Quiz getQuizById(Long id) {
        Optional<Quiz> requestQuiz = quizRepository.findById(id);
        if (!requestQuiz.isPresent()){
            throw new EntityNotFoundException(String.format("Quiz with id '%s' is not found",id));
        }
        return requestQuiz.get();
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public void deleteQuizById(Long id) {
        quizRepository.deleteById(id);
    }

    public Quiz updateQuiz(Long id, QuizDTO quizDTO) {
        Optional<Quiz> quizFromDataBase = quizRepository.findById(id);
        if (!quizFromDataBase.isPresent()) {
            throw new EntityNotFoundException(String.format("Quiz with id '%s' is not found",id));
        }
        Quiz quizToUpdate = quizFromDataBase.get();
        quizToUpdate.setTitle(quizDTO.getTitle());
        return quizToUpdate;
    }
}
