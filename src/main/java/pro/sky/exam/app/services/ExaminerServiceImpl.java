package pro.sky.exam.app.services;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Collection<Question> result = new ArrayList<>();
        if (amount > questionService.getAll().size()) {
            throw new BadRequestException("Запрошено вопросов больше, чем есть в базе данных");
        }
        for (int i = 0; i < amount; i++) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
