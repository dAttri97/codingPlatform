package com.phonepe.interview.model;

import com.phonepe.interview.enums.QuestionState;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Data
@SuperBuilder
public class Contestant extends User implements Comparable<Contestant> {
    private int score;
    private Map<QuestionState, List<Long>> question;

    @Override
    public int compareTo(Contestant o) {
        return Integer.compare(o.getScore(), this.getScore());
    }
}

