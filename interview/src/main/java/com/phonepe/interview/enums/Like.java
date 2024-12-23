package com.phonepe.interview.enums;

import lombok.Data;

public enum Like {
    LIKE("Like", 1),
    DISLIKE("DisLike", -1);

    private String name;
    private int value;

    Like(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
