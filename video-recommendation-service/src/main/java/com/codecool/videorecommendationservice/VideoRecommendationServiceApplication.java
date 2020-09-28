package com.codecool.videorecommendationservice;

import com.codecool.videorecommendationservice.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class VideoRecommendationServiceApplication {

	@Autowired
	private RecommendationRepository recommendationRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoRecommendationServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/recommendations/**")).build();
	}

	@Bean
	@Profile("production")
	public CommandLineRunner init(){
		return args -> {
			Recommendation recommendation = Recommendation.builder().videoId(1L).id(1L).rating(4)
					.comment("Vao very good video, thanks!").build();
			recommendationRepository.save(recommendation);
		};

	}
}
