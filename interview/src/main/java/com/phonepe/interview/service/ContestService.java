package com.phonepe.interview.service;

import com.phonepe.interview.dao.ContestDao;
import com.phonepe.interview.dao.ContestantDao;
import com.phonepe.interview.dao.QuestionLibraryDao;
import com.phonepe.interview.enums.QuestionState;
import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.model.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContestService {
    @Autowired
    private ContestDao contestDao;
    @Autowired
    private QuestionLibraryDao questionLibraryDao;
    @Autowired
    private ContestantDao contestantDao;

    public void submit(Map<String, String> request) {
        Submission submission = Submission.builder()
                .contestantId(Long.valueOf(request.get("contestantId")))
                .questionId(Long.valueOf(request.get("questionId")))
                .score(Double.valueOf(request.get("score")))
                .state(QuestionState.fromName(request.get("state")))
                .build();
        contestDao.submit(submission);
        if(QuestionState.fromName(request.get("state")).equals(QuestionState.SOLVED)) {
            int questionScore = questionLibraryDao.getQuestionById(Long.valueOf(request.get("questionId"))).getScore();
            Contestant contestant = contestantDao.getContestantById(Long.valueOf(request.get("contestantId")));
            contestant.setScore(contestant.getScore() + questionScore);
            contestantDao.updateContestant(contestant);
        }
    }

    public List<String> viewLeaderBoard() {
        return contestantDao.getLeaderBoard(10);
    }


}
