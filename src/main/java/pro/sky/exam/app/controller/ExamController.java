package pro.sky.exam.app.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.exam.app.dao.Question;
import pro.sky.exam.app.service.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping(path = "/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return service.getQuestions(amount);
    }
}
