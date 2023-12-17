package com.ahmed.spring_reddit_clone.repository;


import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.Subreddit;
import com.ahmed.spring_reddit_clone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<List<Post>> findAllBySubreddit(Subreddit subreddit);
    Optional<List<Post>> findAllByUser(User user);
}
