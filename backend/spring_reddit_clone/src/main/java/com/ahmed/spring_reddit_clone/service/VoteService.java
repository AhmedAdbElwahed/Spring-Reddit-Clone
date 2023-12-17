package com.ahmed.spring_reddit_clone.service;


import com.ahmed.spring_reddit_clone.dto.VoteDto;
import com.ahmed.spring_reddit_clone.exception.PostNotFoundException;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.Vote;
import com.ahmed.spring_reddit_clone.model.VoteType;
import com.ahmed.spring_reddit_clone.repository.PostRepository;
import com.ahmed.spring_reddit_clone.repository.VoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;


    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID -> " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository
                .findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }

        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVot(voteDto, post));
        postRepository.save(post);

    }

    private Vote mapToVot(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
