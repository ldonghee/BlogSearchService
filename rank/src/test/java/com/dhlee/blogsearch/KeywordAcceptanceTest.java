package com.dhlee.blogsearch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import com.dhlee.blogsearch.model.KeywordResult;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class KeywordAcceptanceTest extends AcceptanceTest {

	private static final String RANK_BASE_PATH = "/rank";

	@DisplayName("키워드 저장한다.")
	@Test
	void saveKeyword() {
		// given & when
		ExtractableResponse<Response> response = 키워드_저장_요청("TEST");
		// then
		키워드_저장됨(response);
	}

	@DisplayName("키워드 TOP10 목록 조회한다.")
	@Test
	void getRankKeywordList() {
		// given
		키워드_저장_요청("TEST");
		키워드_저장_요청("TEST");
		키워드_저장_요청("Blog");
		키워드_저장_요청("Blog");
		키워드_저장_요청("Blog");
		키워드_저장_요청("테스트");

		// when
		ExtractableResponse<Response> response = 키워드_목록_조회_요청();

		// then
		키워드_목록_조회_응답됨(response);
		키워드_목록_결과_확인됨(response);
	}


	@DisplayName("키워드 조회한다.")
	@Test
	void getKeyword() {
		// given
		ExtractableResponse<Response> saveResponse = 키워드_저장_요청("Blog");
		KeywordResult saveResult = saveResponse.jsonPath().getObject("result", KeywordResult.class);

		// when
		ExtractableResponse<Response> response = 키워드_조회_요청(saveResult.getId());

		// then
		키워드_조회_응답됨(response);
		키워드_조회_결과_확인됨(response, saveResult);
	}

	public static ExtractableResponse<Response> 키워드_저장_요청(String query) {
		return RestAssured.given().log().all()
						  .contentType(MediaType.APPLICATION_JSON_VALUE)
						  .when()
						  .patch(RANK_BASE_PATH + "/keyword?query=" + query)
						  .then().log().all()
						  .extract();
	}

	public static void 키워드_저장됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
	}

	public static ExtractableResponse<Response> 키워드_목록_조회_요청() {
		return RestAssured.given().log().all()
						  .when()
						  .get(RANK_BASE_PATH + "/keywordList")
						  .then().log().all()
						  .extract();
	}

	public static ExtractableResponse<Response> 키워드_조회_요청(Long id) {
		return RestAssured.given().log().all()
						  .when()
						  .get(RANK_BASE_PATH + "/keyword/" + id)
						  .then().log().all()
						  .extract();
	}

	public static void 키워드_목록_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 키워드_목록_결과_확인됨(ExtractableResponse<Response> response) {
		List<KeywordResult> resultList = new ArrayList<>(response.jsonPath()
																.getList("result", KeywordResult.class));
		assertThat(resultList.isEmpty()).isFalse();
		assertThat(resultList.get(0).getQuery()).isEqualTo("Blog");
		assertThat(resultList.get(1).getQuery()).isEqualTo("TEST");
		assertThat(resultList.get(2).getQuery()).isEqualTo("테스트");
	}

	public static void 키워드_조회_응답됨(ExtractableResponse<Response> response) {
		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
	}

	public static void 키워드_조회_결과_확인됨(ExtractableResponse<Response> response, KeywordResult keywordResult) {
		KeywordResult result = response.jsonPath().getObject("result", KeywordResult.class);

		assertThat(result).isNotNull();
		assertThat(result.getQuery()).isEqualTo(keywordResult.getQuery());
	}
}
