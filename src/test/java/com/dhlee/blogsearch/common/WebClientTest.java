package com.dhlee.blogsearch.common;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class WebClientTest {

	@Autowired
	WebClient webClient;

	@Test
	public void test() {
		final String BLOG_SEARCH_BASE_URL = "https://dapi.kakao.com/v2/search/blog";
		final String REST_API_KEY = "5b173ff11c3ab87902301c762eff4b1e";

		StringBuilder queryBuilder = new StringBuilder();

		queryBuilder.append(BLOG_SEARCH_BASE_URL)
					.append("?")
					.append("sort=accuracy")
					.append("&")
					.append("page=1")
					.append("&")
					.append("size=10")
					.append("&")
					.append("query=test");

		Map result = webClient.get()
							  .uri(queryBuilder.toString())
							  .header("Authorization", "KakaoAK " + REST_API_KEY)
							  .retrieve()
							  .bodyToMono(Map.class)
							  .block();


	}
}
