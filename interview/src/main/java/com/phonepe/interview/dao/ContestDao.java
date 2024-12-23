package com.phonepe.interview.dao;

import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.model.Question;
import com.phonepe.interview.model.Submission;
import jakarta.annotation.PostConstruct;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.JstlUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ContestDao {
    static long submissionId;
    List<Submission> submissionList;

    @PostConstruct
    void init() {
        submissionList = new ArrayList<>();
        submissionId = 0;
    }

    public synchronized long submit(Submission submission) {
        submission.setId(submissionId++);
        submissionList.add(submission);
        return submission.getId();
    }



}
