package pro.sky.exam.app.service;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dao.Question;
import pro.sky.exam.app.dao.JavaQuestionRepository;
import pro.sky.exam.app.exception.EmptyQuestionListException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final JavaQuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(JavaQuestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Question add(Question question) {
        return repository.add(question);
    }


    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question remove(Question question) {
       return repository.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return repository.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        List<Question> tmp = new ArrayList<>(getAll());
        int size = tmp.size();

        if (tmp.isEmpty()) {
            throw new EmptyQuestionListException("Список вопросов пуст");
        }
        int index = random.nextInt(size);
        return tmp.get(index);
    }
}
