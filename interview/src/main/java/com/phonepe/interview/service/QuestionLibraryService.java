package com.phonepe.interview.service;

import com.phonepe.interview.dao.QuestionLibraryDao;
import com.phonepe.interview.enums.Difficulty;
import com.phonepe.interview.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuestionLibraryService {
    @Autowired
    private QuestionLibraryDao questionLibraryDao;

    public long addQuestion(Map<String, String> request) {
        Question question = Question.buildQuestion(request);
        questionLibraryDao.createQuestion(question);
        return question.getId();
    }

    public Question getQuestionById(long id) {
        return questionLibraryDao.getQuestionById(id);
    }

    public List<Question> getQuestionByDifficulty(Difficulty difficulty) {
        return questionLibraryDao.getQuestionByDifficulty(difficulty);
    }

}
