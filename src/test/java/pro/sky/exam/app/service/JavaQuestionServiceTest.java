package pro.sky.exam.app.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pro.sky.exam.app.dao.Question;
import pro.sky.exam.app.dao.QuestionRepository;
import pro.sky.exam.app.dao.QuestionRepositoryImpl;
import pro.sky.exam.app.exception.EmptyQuestionListException;
import pro.sky.exam.app.exception.QuestionAlreadyExistsException;
import pro.sky.exam.app.exception.QuestionNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.exam.app.Constants.*;

class JavaQuestionServiceTest {

    private final QuestionRepository repository = new QuestionRepositoryImpl();
    private final QuestionService questionService = new JavaQuestionService(repository);

    @AfterEach
    void cleanUp() {
        List<Question> tmp = new ArrayList<>(questionService.getAll());
        tmp.stream().filter(Objects::nonNull).forEachOrdered(questionService::remove);
    }

    @Test
    void shouldAddQuestionAndAnswerToDataBase() {
        Question expected = new Question(QUESTION, ANSWER);
        Question actual = questionService.add(QUESTION, ANSWER);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowQuestionAlreadyExistsExceptionWhenAddQuestionToDataBase() {
        questionService.add(QUESTION, ANSWER);
        Throwable thrown = catchThrowable(() -> questionService.add(QUESTION, ANSWER));
        assertThat(thrown).isInstanceOf(QuestionAlreadyExistsException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }

    @Test
    void shouldAddQuestionAsObjectToDataBase() {
        Question expected = new Question(QUESTION, ANSWER);
        assertEquals(expected, questionService.add(expected));
        assertFalse(questionService.getAll().isEmpty());
    }

    @Test
    void shouldRemoveQuestionFromDataBase() {
        Question expected = new Question(QUESTION, ANSWER);
        questionService.add(expected);
        Question actual = questionService.remove(expected);
        assertEquals(expected, actual);
        assertTrue(questionService.getAll().isEmpty());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenTriesToRemoveQuestionFromDataBase() {
        Question expected = new Question(QUESTION, ANSWER);
        Throwable thrown = catchThrowable(() -> questionService.remove(expected));
        assertThat(thrown).isInstanceOf(QuestionNotFoundException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }

    @Test
    void shouldShowCollectionOfExistingQuestions() {
        Question expected = questionService.add(QUESTION, ANSWER);
        assertTrue(questionService.getAll().contains(expected));
    }

    @Test
    void shouldReturnEmptyCollection() {
        assertTrue(questionService.getAll().isEmpty());
    }

    @Test
    void shouldShowRandomQuestion() {
        Question expected = questionService.add(QUESTION, ANSWER);
        assertEquals(questionService.getRandomQuestion(), expected);
    }

    @Test
    void shouldThrowExceptionWhenTriesToGetARandomQuestion() {
        Throwable thrown = catchThrowable(questionService::getRandomQuestion);
        assertThat(thrown).isInstanceOf(EmptyQuestionListException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }
}