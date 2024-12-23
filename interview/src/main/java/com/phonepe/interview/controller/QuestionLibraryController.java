package com.phonepe.interview.controller;

import com.phonepe.interview.enums.Difficulty;
import com.phonepe.interview.model.Question;
import com.phonepe.interview.service.QuestionLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionLibraryController {
    @Autowired
    QuestionLibraryService libraryService;

    @PostMapping
    public ResponseEntity<String> createQuestion(@RequestBody Map<String, String> request) {
        try {
            long id = libraryService.addQuestion(request);
            return new ResponseEntity<>(Long.toString(id), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET: Retrieve an item by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getItem(@PathVariable Long id) {
        try {
            Question question = libraryService.getQuestionById(id);
            return new ResponseEntity<>(question.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{difficulty}")
    public ResponseEntity<String> getItem(@PathVariable String difficulty) {
        try {
            List<Question> question = libraryService.getQuestionByDifficulty(Difficulty.fromName(difficulty));
            return new ResponseEntity<>(question.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
