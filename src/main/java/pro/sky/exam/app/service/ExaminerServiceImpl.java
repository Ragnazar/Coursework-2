package pro.sky.exam.app.service;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dao.Question;
import pro.sky.exam.app.exception.BadRequestException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        Set<Question> result = new HashSet<>();
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new BadRequestException("Запрошено вопросов больше, чем есть в базе данных");
        }

        while (result.size() < amount) {
            result.add(questionService.getRandomQuestion());
        }
        return result;
    }
}
