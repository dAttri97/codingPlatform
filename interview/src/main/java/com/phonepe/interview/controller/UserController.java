package com.phonepe.interview.controller;

import com.phonepe.interview.model.Contestant;
import com.phonepe.interview.service.ContestantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ContestantService contestantService;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> request) {
        try {
            long id = contestantService.registerUser(request);
            return new ResponseEntity<>(Long.toString(id), HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET: Retrieve an item by ID
    @GetMapping("/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        try {
            Contestant contestant = contestantService.getUser(id);
            return new ResponseEntity<>(contestant.toString(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
