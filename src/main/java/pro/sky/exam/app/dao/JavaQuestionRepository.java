package pro.sky.exam.app.dao;

import java.util.Collection;

public interface JavaQuestionRepository {
    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();
}
