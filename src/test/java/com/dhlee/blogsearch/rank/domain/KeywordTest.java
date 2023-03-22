package com.dhlee.blogsearch.rank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KeywordTest {

	@Test
	@DisplayName("카운트 증가 테스트")
	public void increase() {
		// given
		Keyword keyword = new Keyword("TEST");

		// when
		keyword.increase();
		keyword.increase();

		// then
		assertThat(keyword.getCount()).isEqualTo(2);
	}

	@Test
	@DisplayName("카운트 변경 테스트")
	public void update() {
		// given
		Keyword keyword = new Keyword("TEST");

		// when
		keyword.setCount(10);

		// then
		assertThat(keyword.getCount()).isEqualTo(10);
	}
}
