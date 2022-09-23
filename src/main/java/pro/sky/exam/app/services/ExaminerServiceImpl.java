package pro.sky.exam.app.services;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.BadRequestException;

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
        int i = 0;
        Set<Question> result = new HashSet<>();
        if (amount > questionService.getAll().size() || amount <= 0) {
            throw new BadRequestException("Запрошено вопросов больше, чем есть в базе данных");
        }

       while (i  < amount) {
            result.add(questionService.getRandomQuestion());
            i++;
        }
        return result;
    }
}
