package pro.sky.exam.app.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.BadRequestException;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static pro.sky.exam.app.Constants.*;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    private ExaminerService examinerService;

    @BeforeEach
    void init() {
        examinerService = new ExaminerServiceImpl(questionService);
        Set<Question> tmp = new HashSet<>(List.of(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        ));
        when(questionService.getAll()).thenReturn(tmp);
        when(questionService.getRandomQuestion()).thenReturn(new Question("question1", "answer1"));
    }

    @Test
    void shouldReturnCollectionWithOneQuestion() {
        assertEquals(examinerService.getQuestions(ONE),
                new HashSet<>(Set.of(new Question("question1", "answer1"))));

    }

    @Test
    @MockitoSettings(strictness = Strictness.LENIENT)
    void shouldThrowBadRequestExceptionWhenParamsAreNotAllowed() {
        Throwable thrown = catchThrowable(() -> examinerService.getQuestions(MINUS_ONE));
        assertThat(thrown).isInstanceOf(BadRequestException.class);
        assertThat(thrown.getMessage()).isNotBlank();

        thrown = catchThrowable(() -> examinerService.getQuestions(Integer.MAX_VALUE));
        assertThat(thrown).isInstanceOf(BadRequestException.class);
        assertThat(thrown.getMessage()).isNotBlank();
    }

}