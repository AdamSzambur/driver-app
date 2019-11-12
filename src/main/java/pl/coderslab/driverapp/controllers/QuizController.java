package pl.coderslab.driverapp.controllers;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.driverapp.DTO.QuizDTO;
import pl.coderslab.driverapp.models.Quiz;
import pl.coderslab.driverapp.services.QuizService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("admin/quizzes")
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

    @PostMapping
    public ResponseEntity<Void> createNewQuiz(@Valid @RequestBody QuizDTO quizDTO
            , UriComponentsBuilder uriComponentsBuilder) {
        Long id = quizService.createNewQuiz(quizDTO);
        UriComponents uriComponents = uriComponentsBuilder.path("/quizzes/{id}").buildAndExpand(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());
        return  new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
