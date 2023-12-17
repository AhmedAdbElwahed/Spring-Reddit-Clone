package com.ahmed.spring_reddit_clone.service;



import com.ahmed.spring_reddit_clone.dto.CommentDto;
import com.ahmed.spring_reddit_clone.exception.PostNotFoundException;
import com.ahmed.spring_reddit_clone.mapper.CommentMapper;
import com.ahmed.spring_reddit_clone.model.Comment;
import com.ahmed.spring_reddit_clone.model.NotificationEmail;
import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.User;
import com.ahmed.spring_reddit_clone.repository.CommentRepository;
import com.ahmed.spring_reddit_clone.repository.PostRepository;
import com.ahmed.spring_reddit_clone.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(() ->
                new PostNotFoundException(commentDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = authService.getCurrentUser().getUsername()
                + " posted a comment on your post." + post.getUrl();
        sendCommentNotification(message, authService.getCurrentUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(
                new NotificationEmail(user.getUsername() + " comment on your post",
                user.getEmail()
                , message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post =  postRepository.findById(postId).orElseThrow(() ->
                new PostNotFoundException(postId.toString()));
        return commentRepository.findByPost(post)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getAllCommentsForUser(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(username));
        return commentRepository.findByUser(user)
                .stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
