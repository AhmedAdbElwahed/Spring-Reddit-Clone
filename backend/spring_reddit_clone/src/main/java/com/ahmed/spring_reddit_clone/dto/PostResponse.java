package com.ahmed.spring_reddit_clone.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String postName;
    private String url;
    private String username;
    private String description;
    private String subredditName;
    //new field
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
}
