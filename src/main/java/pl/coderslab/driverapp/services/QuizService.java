package pl.coderslab.driverapp.services;

import org.springframework.stereotype.Service;
import pl.coderslab.driverapp.DTO.QuizDTO;
import pl.coderslab.driverapp.exceptions.EntityNotFoundException;
import pl.coderslab.driverapp.models.Quiz;
import pl.coderslab.driverapp.repositories.AnswerRepository;
import pl.coderslab.driverapp.repositories.QuestionRepository;
import pl.coderslab.driverapp.repositories.QuizRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuizService {



    private QuizRepository quizRepository;
    private QuestionRepository questionRepository;
    private AnswerRepository answerRepository;

    public QuizService(QuizRepository quizRepository, QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
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
        Optional<Quiz> requestQuiz = quizRepository.findById(id);
        if (!requestQuiz.isPresent()) {
            throw new EntityNotFoundException(String.format("Quiz with id '%s' is not found", id));
        }

        Quiz quizFromDataBase = requestQuiz.get();
        quizFromDataBase.getQuestions().stream()
                .forEach(q-> {
                            q.getAnswers().stream()
                                    .forEach(a -> answerRepository.delete(a));
                            questionRepository.delete(q);
                        }
                );
        quizRepository.delete(quizFromDataBase);
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

    public Long createNewQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz = quizRepository.save(quiz);
        return quiz.getId();
    }
}
