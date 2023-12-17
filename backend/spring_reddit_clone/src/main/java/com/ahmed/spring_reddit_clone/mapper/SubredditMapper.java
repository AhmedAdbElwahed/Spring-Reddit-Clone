package com.ahmed.spring_reddit_clone.mapper;


import com.ahmed.spring_reddit_clone.dto.SubredditDto;
import com.ahmed.spring_reddit_clone.model.Post;
import com.ahmed.spring_reddit_clone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);
    
    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtotoSubreddit(SubredditDto subredditDto);

    default Integer mapPosts(List<Post> posts) {
        return posts.size();
    }
}
