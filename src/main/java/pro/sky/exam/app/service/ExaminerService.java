package pro.sky.exam.app.service;

import pro.sky.exam.app.dao.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
