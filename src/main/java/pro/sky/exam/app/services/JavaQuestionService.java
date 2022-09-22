package pro.sky.exam.app.services;

import org.springframework.stereotype.Service;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.EmptyQuestionListException;
import pro.sky.exam.app.exceptions.QuestionAlreadyExistsException;
import pro.sky.exam.app.exceptions.QuestionNotFoundException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Map<String, Question> questions = new HashMap<>();

    @Override
    public Question add(String question, String answer) {
        String key = question + answer;
        if (questions.containsKey(key)) {
            throw new QuestionAlreadyExistsException("Вопрос уже есть в списке");
        }
        Question q = new Question(question, answer);
        questions.put(key, q);
        return q;
    }

    @Override
    public Question add(Question question) {
        String key = question.getQuestion() + question.getAnswer();
        if (questions.containsKey(key)) {
            throw new QuestionAlreadyExistsException("Вопрос уже есть в списке");
        }
        questions.put(key, question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        String key = question.getQuestion() + question.getAnswer();
        if (!questions.containsKey(key)) {
            throw new QuestionNotFoundException("Вопрос не найден в списке");
        }
        Question q = questions.get(key);
        questions.remove(key);
        return q;
    }

    @Override
    public Collection<Question> getAll() {
        return new ArrayList<>(questions.values());
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        List<Question> tmp = new ArrayList<>(getAll());
        int size = tmp.size();
        if (size == 0) {
            throw new EmptyQuestionListException("Список вопросов пуст");
        }
        int index = random.nextInt(size);
        return tmp.get(index);
    }
}
