package pl.coderslab.driverapp.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.driverapp.DTO.QuizDTO;
import pl.coderslab.driverapp.models.Quiz;
import pl.coderslab.driverapp.services.QuizService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return  ResponseEntity.ok(quizService.getQuizById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody @Valid QuizDTO quizDTO) {
        return ResponseEntity.ok(quizService.updateQuiz(id,quizDTO));
    }


}
