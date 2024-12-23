package com.phonepe.interview.service;

import com.phonepe.interview.dao.ContestantDao;
import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ContestantService {
    @Autowired
    private ContestantDao contestDao;

    public Long registerUser(Map<String, String> request) {
        Contestant contestant = (Contestant) User.builder()
                .name(request.get("name"))
                .email(request.get("email"))
                .build();
        return contestDao.createContestant(contestant);
    }

    public Contestant getUser(Long id) {
        return contestDao.getContestantById(id);
    }
}
