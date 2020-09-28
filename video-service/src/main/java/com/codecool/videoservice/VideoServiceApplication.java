package com.codecool.videoservice;

import com.codecool.videoservice.model.Video;
import com.codecool.videoservice.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication
public class VideoServiceApplication {

	@Autowired
	private VideoRepository videoRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoServiceApplication.class, args);
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Bean
	@Profile("production")
	public CommandLineRunner init(){
		return args -> {
			Video digitalism_JPEG = Video.builder().id(1L).name("Digitalism - JPEG").url("https://www.youtube.com/watch?v=Znv_aMZXZQM").build();
			videoRepository.save(digitalism_JPEG);
		};

	}
}
