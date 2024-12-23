package com.phonepe.interview.model;

import com.phonepe.interview.enums.QuestionState;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Submission {
    private long id;
    private Long contestantId;
    private Long questionId;
    private QuestionState state;
    private double score;
}
