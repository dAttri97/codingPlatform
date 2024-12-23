package com.phonepe.interview.dao;

import com.phonepe.interview.enums.QuestionState;
import com.phonepe.interview.exception.ContestantException;
import com.phonepe.interview.exception.QuestionLibraryException;
import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.model.Question;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@Component
public class ContestantDao {
    private static long id;
    private static Map<Long, Contestant> contestants;
    private static Set<String> emailSet;

    @PostConstruct
    void init() {
        id = 0;
        contestants = new HashMap<>();
        emailSet = new HashSet<>();
    }

    public synchronized long createContestant(Contestant contestant) {
        if(emailSet.contains(contestant.getEmail())) {
            throw new ContestantException("Contestant already exists");
        }
        this.id++;
        contestant.setId(id);
        contestants.put(id, contestant);
        emailSet.add(contestant.getEmail());
        log.info("Question successfully added");
        return contestant.getId();
    }

    public Contestant getContestantById(long id) {
        if(!contestants.containsKey(id)) {
            throw new ContestantException("Invalid User id");
        }
        return contestants.get(id);
    }

    public Contestant updateContestant(Contestant contestant) {
        if(!contestants.containsKey(contestant.getId())) {
            throw new ContestantException("Invalid User id");
        }
        return contestants.put(contestant.getId(), contestant);
    }

    public List<Long> solvedQuestion(Long id, QuestionState state) {
        if(!contestants.containsKey(id)) {
            throw new ContestantException("Invalid user id");
        }
        Contestant contestant = contestants.get(id);
        return contestant.getQuestion().get(state);
    }

    public List<String> getLeaderBoard(int size) {
        List<Contestant> contestantList = contestants.entrySet().stream()
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
        Collections.sort(contestantList);
        return (contestantList.stream().map(contestant -> contestant.getName()).collect(Collectors.toList())).subList(0, size);
    }


}
