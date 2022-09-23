package pro.sky.exam.app.controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.services.ExaminerService;

import java.util.Collection;

@RestController
public class ExamController {
    private final ExaminerService service;

    public ExamController(ExaminerService service) {
        this.service = service;
    }

    @GetMapping(path = "/get/{amount}")
    @ResponseBody
    public Collection<Question> getQuestions(@PathVariable("amount") int amount) {
        return service.getQuestions(amount);
    }
}
