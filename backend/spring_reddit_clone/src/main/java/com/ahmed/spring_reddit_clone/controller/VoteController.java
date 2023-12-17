package com.ahmed.spring_reddit_clone.controller;

import com.ahmed.spring_reddit_clone.dto.VoteDto;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/votes")
public class VoteController {

    public final VoteService voteService;

    @PostMapping
    public ResponseEntity<?> vote(@RequestBody VoteDto voteDto) {
        try {
            voteService.vote(voteDto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SpringRedditException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
