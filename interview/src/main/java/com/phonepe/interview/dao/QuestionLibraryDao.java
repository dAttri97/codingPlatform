package com.phonepe.interview.dao;

import com.phonepe.interview.enums.Difficulty;
import com.phonepe.interview.enums.Rating;
import com.phonepe.interview.enums.Tag;
import com.phonepe.interview.exception.QuestionLibraryException;
import com.phonepe.interview.model.Question;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
public class QuestionLibraryDao {

    private static long id;
    private static Map<Long, Question> questionLibrary;
    private static Set<String> questionNameSet;

    @PostConstruct
    void init() {
        id = 0;
        questionLibrary = new HashMap<>();
        questionNameSet = new HashSet<>();
    }

    public synchronized long createQuestion(Question question) {
        if(questionNameSet.contains(question.getName())) {
            throw new QuestionLibraryException("Question already exists");
        }
        this.id++;
        question.setId(id);
        questionLibrary.put(id, question);
        questionNameSet.add(question.getName());
        log.info("Question successfully added");
        return question.getId();
    }

    public void rateQuestion(long id, Rating rating) {
        if(!questionLibrary.containsKey(id)) {
            throw new QuestionLibraryException("Invalid question id");
        }
        final Question question = questionLibrary.get(id);
        Question.updateRating(question, rating);
    }

    public Question getQuestionById(long id) {
        if(!questionLibrary.containsKey(id)) {
            throw new QuestionLibraryException("Invalid question id");
        }
        return questionLibrary.get(id);
    }

    public List<Question> getQuestionByDifficulty(Difficulty difficulty) {
        return questionLibrary.entrySet().stream().filter(entry -> entry.getValue().getDifficulty().equals(difficulty))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    public List<Question> getQuestionByTag(Tag tag) {
        return questionLibrary.entrySet().stream().filter(entry -> entry.getValue().getTag().equals(tag))
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

}
