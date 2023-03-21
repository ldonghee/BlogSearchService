package com.dhlee.blogsearch.search.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.search.domain.kakao.KakaoSearchEngine;
import com.dhlee.blogsearch.search.domain.kakao.KakaoSearchParam;
import com.dhlee.blogsearch.search.domain.naver.NaverSearchEngine;
import com.dhlee.blogsearch.search.domain.naver.NaverSearchParam;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

@SpringBootTest
public class SearchEngineTest {

	@Autowired
	private WebClient webClient;

	@Test
	@DisplayName("카카오 검색 테스트 ")
	public void Kakao_Search() {
		// given
		KakaoSearchEngine engine = new KakaoSearchEngine(new KakaoSearchParam());

		// when
		List<SearchResult> actual = engine.search(webClient, new SearchRequest("test", "accuracy", 1, 1));

		assertThat(actual).isNotNull();
		assertThat(actual.size()).isEqualTo(1);
		assertThat(actual.get(0).getBlogName()).isNotEmpty();
		assertThat(actual.get(0).getContents()).isNotEmpty();
		assertThat(actual.get(0).getDatetime()).isNotEmpty();
		assertThat(actual.get(0).getTitle()).isNotEmpty();
		assertThat(actual.get(0).getUrl()).isNotEmpty();
	}

	@Test
	@DisplayName("네이버 검색 테스트 ")
	public void Naver_Search() {
		// given
		NaverSearchEngine engine = new NaverSearchEngine(new NaverSearchParam());

		// when
		List<SearchResult> actual = engine.search(webClient, new SearchRequest("test", "sim", 1, 1));

		assertThat(actual).isNotNull();
		assertThat(actual.size()).isEqualTo(1);
		assertThat(actual.get(0).getBlogName()).isNotEmpty();
		assertThat(actual.get(0).getContents()).isNotEmpty();
		assertThat(actual.get(0).getDatetime()).isNotEmpty();
		assertThat(actual.get(0).getTitle()).isNotEmpty();
		assertThat(actual.get(0).getUrl()).isNotEmpty();
	}
}
