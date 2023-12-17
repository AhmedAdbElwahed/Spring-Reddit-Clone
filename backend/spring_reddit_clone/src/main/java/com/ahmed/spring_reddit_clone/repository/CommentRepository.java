package com.ahmed.spring_reddit_clone.repository;


import com.ahmed.spring_reddit_clone.model.Comment;
import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByUser(User user);
    List<Comment> findByPost(Post post);
}
