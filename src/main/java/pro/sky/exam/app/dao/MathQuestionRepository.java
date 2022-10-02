package pro.sky.exam.app.dao;

import java.util.Collection;

public interface MathQuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
