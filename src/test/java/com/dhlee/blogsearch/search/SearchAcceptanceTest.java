package com.dhlee.blogsearch.search;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.dhlee.blogsearch.common.AcceptanceTest;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class SearchAcceptanceTest extends AcceptanceTest {

	private static final String BLOG_SEARCH_BASE_PATH = "/search/blog";

	@DisplayName("블로그 검색 조회한다.")
	@Test
	void getBlogSearch() {
		// given
		SearchRequest searchRequest = new SearchRequest("TEST", "accuracy", 1, 5);
		// when
		ExtractableResponse<Response> response = 블로그_검색_조회_요청(searchRequest);
		// then
		블로그_검색_조회_응답됨(response);
		블로그_검색_결과_확인됨(response);
	}



	public static ExtractableResponse<Response> 블로그_검색_조회_요청(SearchRequest request) {
		return RestAssured.given().log().all()
						  .when()
						  .get(BLOG_SEARCH_BASE_PATH)
						  .then().log().all()
						  .extract();
	}

	public static void 블로그_검색_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 블로그_검색_결과_확인됨(ExtractableResponse<Response> response) {
		List<SearchResult> resultList = new ArrayList<>(response.jsonPath()
																.getList("result", SearchResult.class));
		assertThat(resultList.isEmpty()).isFalse();
		assertThat(resultList.get(0).getTitle().isEmpty()).isFalse();
		assertThat(resultList.get(0).getUrl().isEmpty()).isFalse();
		assertThat(resultList.get(0).getContents().isEmpty()).isFalse();
		assertThat(resultList.get(0).getBlogName().isEmpty()).isFalse();
		assertThat(resultList.get(0).getDatetime().isEmpty()).isFalse();
	}
}
