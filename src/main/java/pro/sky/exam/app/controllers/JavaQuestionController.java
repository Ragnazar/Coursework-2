package pro.sky.exam.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.services.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(QuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer) throws HttpClientErrorException.BadRequest {
        return service.add(question, answer);
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer) throws HttpClientErrorException.BadRequest {
        Question target = new Question(question, answer);
        return service.remove(target);
    }

    @GetMapping(path = "/")
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}
