package com.ahmed.spring_reddit_clone.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(String exception) {
        super(exception);
    }
}
