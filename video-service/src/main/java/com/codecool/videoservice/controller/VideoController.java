package com.codecool.videoservice.controller;

import com.codecool.videoservice.model.Recommendation;
import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repository.VideoRepository;
import com.codecool.videoservice.service.RecommendationsCaller;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
    public String getAllVideos() throws JSONException {
         List<Video> everyVideo = videoRepository.findAll();
         JSONArray jsonArray = new JSONArray();
         for (Video video : everyVideo) {
             JSONObject jsonObject = new JSONObject();
             jsonObject.put("name", video.getName());
             jsonObject.put("id", video.getId());
             jsonObject.put("url", video.getUrl());
             jsonObject.put("recommendations", video.getRecommendationList());
             jsonArray.put(jsonObject);
         }
         return jsonArray.toString();
     }

     @GetMapping("/video/{videoId}")
    public String getDetailedPage(@PathVariable("videoId") Long videoId) throws JSONException {
         Video video = videoRepository.findById(videoId).get();
         List<Recommendation> recommendations = recommendationsCaller.getRecommendations(videoId);
         video.setRecommendationList(recommendations);
         JSONObject jsonObject = new JSONObject();
         jsonObject.put("name", video.getName());
         jsonObject.put("url", video.getUrl());
         jsonObject.put("id", video.getId());
         jsonObject.put("recommendations", video.getRecommendationList());
         return jsonObject.toString();
     }


     @PostMapping("/video/{videoId}/add-recommendation")
     public void addRecommendationToVideo(@PathVariable("videoId") Long videoId, @RequestBody Map<String, Object> body){
         String comment = body.get("comment").toString();
         Integer rating = Integer.valueOf(body.get("rating").toString());
         Recommendation recommendation = Recommendation.builder().comment(comment).rating(rating)
                 .videoId(videoId).build();
         recommendationsCaller.saveRecommendation(recommendation);
     }

     @PostMapping("/video/{videoId}")
    public void updateVideoAndRecommendations(@PathVariable("videoId") Long videoId, @RequestBody Map<String, Object> body){

     }


     @PostMapping("/video/add")
    public void addVideo(@RequestBody Map<String, Object> body){
         String url = body.get("url").toString();
         String name = body.get("name").toString();
         Video video = Video.builder().url(url).name(name).build();
         videoRepository.save(video);
     }


}
