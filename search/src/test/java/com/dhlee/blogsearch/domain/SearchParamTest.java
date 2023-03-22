package com.dhlee.blogsearch.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dhlee.blogsearch.domain.kakao.KakaoSearchParam;
import com.dhlee.blogsearch.domain.naver.NaverSearchParam;
import com.dhlee.blogsearch.exception.InvalidArgumentException;
import com.dhlee.blogsearch.model.SearchRequest;

public class SearchParamTest {
	@Test
	@DisplayName("카카오 Query Parameter 조회")
	public void Kakao_GetQueryParameters() {
		// given
		KakaoSearchParam param = new KakaoSearchParam();
		String expect = "?query=test&sort=accuracy&page=1&size=1";
		String expect2 = "?query=test&sort=accuracy&page=1&size=10";
		String expect3 = "?query=test&sort=accuracy&page=1&size=10";
		String expect4 = "?query=test&sort=accuracy&page=1&size=10";

		// when
		String actual = param.getQueryParameters(new SearchRequest("test", "accuracy", 1, 1));
		String actual2 = param.getQueryParameters(new SearchRequest("test", "accuracy", 1));
		String actual3 = param.getQueryParameters(new SearchRequest("test", "accuracy"));
		String actual4 = param.getQueryParameters(new SearchRequest("test"));

		// then
		assertThat(expect).isEqualTo(actual);
		assertThat(expect2).isEqualTo(actual2);
		assertThat(expect3).isEqualTo(actual3);
		assertThat(expect4).isEqualTo(actual4);
	}

	@Test
	@DisplayName("네이버 Query Parameter 조회")
	public void Naver_GetQueryParameters() {
		// given
		String expect = "?query=test&sort=sim&start=1&display=1";
		String expect2 = "?query=test&sort=sim&start=1&display=10";
		String expect3 = "?query=test&sort=sim&start=1&display=10";
		String expect4 = "?query=test&sort=sim&start=1&display=10";
		NaverSearchParam param = new NaverSearchParam();

		// when
		String actual = param.getQueryParameters(new SearchRequest("test", "sim", 1, 1));
		String actual2 = param.getQueryParameters(new SearchRequest("test", "sim", 1));
		String actual3 = param.getQueryParameters(new SearchRequest("test", "sim"));
		String actual4 = param.getQueryParameters(new SearchRequest("test"));

		// then
		assertThat(expect).isEqualTo(actual);
		assertThat(expect2).isEqualTo(actual2);
		assertThat(expect3).isEqualTo(actual3);
		assertThat(expect4).isEqualTo(actual4);
	}

	@Test
	@DisplayName("카카오 Query Parameter 예외 조회")
	public void Kakao_GetQueryParameters_Exception() {
		KakaoSearchParam param = new KakaoSearchParam();
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "accuracy", 1, 100)));
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "accuracy", 100)));
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "accuracy2")));
	}

	@Test
	@DisplayName("네이버 Query Parameter 예외 조회")
	public void Naver_GetQueryParameters_Exception() {
		NaverSearchParam param = new NaverSearchParam();
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "sim", 1, 100)));
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "sim", 100)));
		Assertions.assertThrows(InvalidArgumentException.class, () -> param.getQueryParameters(new SearchRequest("test", "TEST")));
	}

}
