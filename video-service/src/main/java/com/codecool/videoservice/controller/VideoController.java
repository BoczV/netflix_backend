package com.codecool.videoservice.controller;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repository.VideoRepository;
import com.codecool.videoservice.service.RecommendationsCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@CrossOrigin("*")
public class VideoController {

    @Autowired
    VideoRepository videoRepository;


    @Autowired
    RecommendationsCaller recommendationsCaller;


     @GetMapping("/all")
    public String getAllVideos(){
         List<Video> everyVideo = videoRepository.findAll();
         return null;
     }

     @GetMapping("/video/{videoId}")
    public String getDetailedPage(@PathVariable("videoId") Long videoId){
         Video video = videoRepository.findById(videoId).get();
         return null;
     }


     @PostMapping("/video/{videoId}/add-recommendation")
     public void addRecommendationToVideo(@PathVariable("videoId") Long videoId, @RequestBody Map<String, Object> body){

     }

     @PostMapping("/video/{videoId}")
    public void updateVideoAndRecommendations(@PathVariable("videoId") Long videoId, @RequestBody Map<String, Object> body){

     }


}
