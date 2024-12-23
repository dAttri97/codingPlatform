package com.phonepe.interview.enums;

public enum Tag {
    DP("Dynamic Programming"),
    ARRAY("Array Manipulation"),
    STRING("String Operations");

    private final String description;

    // Constructor
    Tag(String description) {
        this.description = description;
    }

    // Getter for description
    public String getDescription() {
        return description;
    }

    // Method to get Tag by name
    public static Tag fromName(String name) {
        for (Tag tag : Tag.values()) {
            if (tag.name().equalsIgnoreCase(name)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Invalid tag name: " + name);
    }
}
