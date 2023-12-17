package com.ahmed.spring_reddit_clone.service;


import com.ahmed.spring_reddit_clone.dto.PostRequest;
import com.ahmed.spring_reddit_clone.dto.PostResponse;
import com.ahmed.spring_reddit_clone.exception.PostNotFoundException;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.exception.SubredditNotFoundException;
import com.ahmed.spring_reddit_clone.mapper.PostMapper;
import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.Subreddit;
import com.ahmed.spring_reddit_clone.model.User;
import com.ahmed.spring_reddit_clone.repository.PostRepository;
import com.ahmed.spring_reddit_clone.repository.SubredditRepository;
import com.ahmed.spring_reddit_clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public void save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findSubredditByName(
                postRequest.getSubredditName())
                .orElseThrow(() -> new SpringRedditException(
                        String.format("No Subreddit Found By Name -> %s", postRequest.getSubredditName())));
        User currentUser = authService.getCurrentUser();
        Post post = postMapper .map(postRequest, subreddit, currentUser);
        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() ->
                new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit).orElseThrow(
                () -> new PostNotFoundException("Post Not found")
        );
        return posts
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(username));
        return postRepository.findAllByUser(user)
                .orElseThrow(() -> new PostNotFoundException("No Post Found"))
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
