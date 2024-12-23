package com.phonepe.interview.enums;

public enum Difficulty {
    EASY("Easy", "0,255,0"),       // Green
    MEDIUM("Medium", "255,165,0"), // Orange
    HARD("Hard", "255,0,0");       // Red

    private final String name;
    private final String color; // RGB format as a string

    // Constructor
    Difficulty(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    // Static method to get DifficultyLevel by name
    public static Difficulty fromName(String name) {
        for (Difficulty level : Difficulty.values()) {
            if (level.name.equalsIgnoreCase(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid difficulty level name: " + name);
    }
}
