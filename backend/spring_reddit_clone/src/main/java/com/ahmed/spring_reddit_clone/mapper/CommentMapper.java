package com.ahmed.spring_reddit_clone.mapper;


import com.ahmed.spring_reddit_clone.dto.CommentDto;
import com.ahmed.spring_reddit_clone.model.Comment;
import com.ahmed.spring_reddit_clone.model.Post;

import com.ahmed.spring_reddit_clone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    @Mapping(target = "user", source = "user")
    Comment map(CommentDto commentDto, Post post, User user);


    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}
