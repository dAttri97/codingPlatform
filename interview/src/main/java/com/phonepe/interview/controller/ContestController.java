package com.phonepe.interview.controller;

import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contest")
public class ContestController {
    @Autowired
    private ContestService contestService;

    // GET: Retrieve an item by ID
    @PostMapping("/submit")
    public ResponseEntity<String> submitQuestion(@RequestBody Map<String, String> request) {
        try {
            contestService.submit(request);
            return new ResponseEntity<>("Submit Successful", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/leaderboard")
    public ResponseEntity<List<String>> getLeaderBoard() {
        return new ResponseEntity<>(contestService.viewLeaderBoard(), HttpStatus.OK);
    }
}
