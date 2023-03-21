package com.dhlee.blogsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class BlogSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSearchApplication.class, args);
	}

}
