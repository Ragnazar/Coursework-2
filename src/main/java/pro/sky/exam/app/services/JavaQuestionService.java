package pro.sky.exam.app.services;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.EmptyQuestionListException;
import pro.sky.exam.app.exceptions.QuestionAlreadyExistsException;
import pro.sky.exam.app.exceptions.QuestionNotFoundException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    @Override
    public Question add(Question question) {
        if (questions.contains(question)) {
            throw new QuestionAlreadyExistsException("Вопрос уже есть в списке");
        }
        questions.add(question);
        return question;
    }


    public Question add(String question, String answer) {
        Question q = new Question(question, answer);
        add(q);
        return q;
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
