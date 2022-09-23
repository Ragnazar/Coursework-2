package pro.sky.exam.app.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.exam.app.dtos.Question;
import pro.sky.exam.app.exceptions.BadRequestException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    private final ExaminerService examinerService;

    ExaminerServiceImplTest(QuestionService questionService, ExaminerService examinerService) {
        this.questionService = questionService;
        this.examinerService = examinerService;
    }


    @Test
    void shouldReturnCollectionOfRandomQuestions() {
        when(examinerService.getQuestions(3)).thenReturn(new ArrayList<>(List.of(
                new Question("question1", "answer1"),
                new Question("question2", "answer2"),
                new Question("question3", "answer3")
        )));
    }

    @Test
    void shouldReturnCollection() {
        assertFalse(examinerService.getQuestions(3).isEmpty());
        assertEquals(examinerService.getQuestions(3).size(), 3);

    }

    @Test
    void shouldThrowBadRequestException() {
        when(examinerService.getQuestions(5))
                .thenThrow(new BadRequestException("Запрошено вопросов больше, чем есть в базе данных"));
        Throwable thrown = catchThrowable(() -> examinerService.getQuestions(5));
        assertThat(thrown).isInstanceOf(BadRequestException.class);
        assertThat(thrown.getMessage()).isNotBlank();

    }

}