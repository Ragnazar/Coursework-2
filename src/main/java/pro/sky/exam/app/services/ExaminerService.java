package pro.sky.exam.app.services;

import pro.sky.exam.app.dtos.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
