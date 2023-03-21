package com.dhlee.blogsearch.rank.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dhlee.blogsearch.rank.domain.Keyword;
import com.dhlee.blogsearch.rank.repository.RankRepository;

@SpringBootTest
public class RankServiceTest {
	private static final ExecutorService service = Executors.newFixedThreadPool(10);

	@Autowired
	private RankRepository rankRepository;

	@Autowired
	private RankService rankService;

	@Test
	@DisplayName("100번의 검색어 동시 접근 시, 횟수 증가 테스트")
	public void lock() throws InterruptedException {
		// given
		String query = "test";
		Keyword keyword = new Keyword(query);
		rankRepository.save(keyword);

		// when
		CountDownLatch latch = new CountDownLatch(100);
		for (int i=0; i < 100; i++) {
			service.execute(() -> {
				rankService.saveKeyword(query);
				latch.countDown();
			});
		}
		latch.await();

		// then
		Keyword actual = rankRepository.findById(keyword.getId()).orElseThrow();
		assertThat(actual.getCount()).isEqualTo(100);
	}
}
