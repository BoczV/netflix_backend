package com.codecool.videoservice.service;

import com.codecool.videoservice.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RecommendationsCaller {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${videorecommendation.url}")
    private String baseUrl;


    public List<Recommendation> getRecommendations(Long videoId){
        System.out.println(videoId);
        return restTemplate.getForEntity(baseUrl + "/" + videoId + "/all", List.class).getBody();
    }

    public String saveRecommendation(Recommendation recommendation) {
        System.out.println(recommendation);
        return restTemplate.postForEntity(baseUrl + "/add", recommendation, String.class).getBody();
    }

}
