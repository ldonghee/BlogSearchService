package com.dhlee.blogsearch.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.model.SearchRequest;
import com.dhlee.blogsearch.model.SearchResult;

@SpringBootTest
public class SearchEngineManagerTest {

	@Autowired
	private WebClient webClient;

	@Test
	@DisplayName("검색 테스트")
	public void search() {
		// given
		SearchEngineManager searchEngineManager = new SearchEngineManager(webClient);
		SearchRequest searchRequest = new SearchRequest("test", "accuracy", 1, 1);

		// when
		List<SearchResult> search = searchEngineManager.search(searchRequest);

		// then
		assertThat(search).isNotNull();
	}
}
