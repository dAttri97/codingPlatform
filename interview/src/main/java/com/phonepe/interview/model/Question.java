package com.phonepe.interview.model;

import com.phonepe.interview.enums.Difficulty;
import com.phonepe.interview.enums.Like;
import com.phonepe.interview.enums.Rating;
import com.phonepe.interview.enums.Tag;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Question {
    private long id;
    private String name;
    private String description;
    private Difficulty difficulty;
    private int score;
    private int likes;
    private int ratingCount;
    private double averageRating;
    private Tag tag;

    public static Question buildQuestion(Map<String, String> values) {
        if (!values.containsKey("name") || values.get("name").isEmpty()) {
            throw new IllegalArgumentException("Field 'name' is required and cannot be empty");
        }
        if (!values.containsKey("description") || values.get("description").isEmpty()) {
            throw new IllegalArgumentException("Field 'description' is required and cannot be empty");
        }
        if (!values.containsKey("difficulty") || values.get("difficulty").isEmpty()) {
            throw new IllegalArgumentException("Field 'difficulty' is required and cannot be empty");
        }
        if (!values.containsKey("tag") || values.get("tag").isEmpty()) {
            throw new IllegalArgumentException("Field 'tag' is required and cannot be empty");
        }
        String name = values.get("name");
        String description = values.get("description");
        Difficulty difficulty = Difficulty.valueOf(values.get("difficulty").toUpperCase());
        Tag tag = Tag.valueOf(values.get("tag").toUpperCase());
        int score = values.containsKey("score") ? Integer.parseInt(values.get("score")) : 0;
        int likes = values.containsKey("likes") ? Integer.parseInt(values.get("likes")) : 0;
        int ratingCount = values.containsKey("ratingCount") ? Integer.parseInt(values.get("ratingCount")) : 0;
        double averageRating = values.containsKey("averageRating") ? Double.parseDouble(values.get("averageRating")) : 0.0;

        return Question.builder()
                .name(name)
                .description(description)
                .difficulty(difficulty)
                .score(score)
                .likes(likes)
                .ratingCount(ratingCount)
                .averageRating(averageRating)
                .tag(tag)
                .build();
    }

    public static int updateLike(Question question, Like like) {
        question.setLikes(question.getLikes() + like.getValue());
        return question.getLikes();
    }

    public static double updateRating(Question question, Rating rating) {
        question.setRatingCount(question.getRatingCount() + 1);
        double totalRating = question.getAverageRating() * question.getRatingCount();
        totalRating += rating.getValue();
        question.setAverageRating(totalRating/(1.0 * question.getRatingCount()));
        return question.getAverageRating();
    }
}
