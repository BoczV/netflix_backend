package com.codecool.videorecommendationservice.controller;

import com.codecool.videorecommendationservice.RecommendationRepository;
import com.codecool.videorecommendationservice.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    RecommendationRepository recommendationRepository;

    @GetMapping("/{videoId}/all")
    public List<Recommendation> getRecommendations(@PathVariable("videoId") Long videoId){
        List<Recommendation> allByVideoId = recommendationRepository.findAllByVideoId(videoId);
        System.out.println(allByVideoId);
        return allByVideoId;
    }

    @PostMapping("/add")
    public void addNewRecommendation(@RequestBody Recommendation recommendation){
        System.out.println(recommendation);
        recommendationRepository.save(recommendation);
    }

}
