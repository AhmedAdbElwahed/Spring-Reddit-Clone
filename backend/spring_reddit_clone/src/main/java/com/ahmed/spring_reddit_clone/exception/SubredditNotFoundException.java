package com.ahmed.spring_reddit_clone.exception;

public class SubredditNotFoundException extends RuntimeException{
    public SubredditNotFoundException(String exception) {
        super(exception);
    }
}
