package pro.sky.exam.app.dao;

import org.springframework.stereotype.Repository;
import pro.sky.exam.app.exception.QuestionAlreadyExistsException;
import pro.sky.exam.app.exception.QuestionNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
@Repository
public class QuestionRepositoryImpl implements QuestionRepository {
    private final Set<Question> questions = new HashSet<>();
    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException("Вопрос уже есть в списке");
        }
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.contains(question)) {
            throw new QuestionNotFoundException("Вопрос не найден в списке");
        }
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

}
