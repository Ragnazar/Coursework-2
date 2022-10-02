package pro.sky.exam.app.services;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.dtos.QuestionRepository;
import pro.sky.exam.app.exceptions.EmptyQuestionListException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final QuestionRepository repository;
    private final Random random = new Random();

    public JavaQuestionService(QuestionRepository repository) {
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
