package com.phonepe.interview.enums;

public enum QuestionState {
    ATTEMPTED("The question has been attempted."),
    SOLVED("The question has been solved."),
    UNSOLVED("The question is yet to be solved.");

    private final String description;

    QuestionState(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static QuestionState fromName(String name) {
        for (QuestionState state : QuestionState.values()) {
            if (state.name().equalsIgnoreCase(name)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Invalid QuestionState: " + name);
    }
}
