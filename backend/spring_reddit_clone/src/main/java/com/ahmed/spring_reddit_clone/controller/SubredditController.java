package com.ahmed.spring_reddit_clone.controller;


import com.ahmed.spring_reddit_clone.dto.SubredditDto;
import com.ahmed.spring_reddit_clone.service.SubredditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subreddit")
@Slf4j
public class SubredditController {

    private SubredditService subredditService;

    @PostMapping
    public ResponseEntity<SubredditDto> createSubreddit(@RequestBody SubredditDto subredditDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(subredditService.saveSubreddit(subredditDto));
    }

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddit() {
        return ResponseEntity.status(HttpStatus.OK).body(subredditService.fetchAllSubreddit());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubredditById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.valueOf(200))
                .body(subredditService.getSubredditById(id));
    }

}
