package com.ahmed.spring_reddit_clone.service;


import com.ahmed.spring_reddit_clone.dto.SubredditDto;
import com.ahmed.spring_reddit_clone.exception.SpringRedditException;
import com.ahmed.spring_reddit_clone.mapper.SubredditMapper;
import com.ahmed.spring_reddit_clone.model.Subreddit;
import com.ahmed.spring_reddit_clone.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubredditService {


    private SubredditRepository subredditRepository;
    private SubredditMapper subredditMapper;


    @Transactional
    public SubredditDto saveSubreddit(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtotoSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;

    }

    public List<SubredditDto> fetchAllSubreddit() {
        return subredditRepository.findAll().stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(Collectors.toList());
    }


    public SubredditDto getSubredditById(Long id) {
        return subredditMapper.mapSubredditToDto(subredditRepository.findById(id).orElseThrow( () ->
                new SpringRedditException(String.format("No Subreddit With The Id -> %d", id))
        ));
    }
}
