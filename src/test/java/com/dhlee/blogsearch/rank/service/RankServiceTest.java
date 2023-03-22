package com.dhlee.blogsearch.rank.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.dhlee.blogsearch.common.config.redis.RedisCacheKey;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.service.SearchKeywordService;

@SpringBootTest
public class RankServiceTest {
	private static final ExecutorService service = Executors.newFixedThreadPool(10);

	@Autowired
	private RedisTemplate redisHashTemplate;

	@Autowired
	private SearchKeywordService searchKeywordService;

	@Test
	@DisplayName("100번의 검색어 동시 접근 시, 횟수 증가 테스트")
	public void lock() throws InterruptedException {
		// given
		String query = "test";

		// when
		CountDownLatch latch = new CountDownLatch(100);
		for (int i=0; i < 100; i++) {
			service.execute(() -> {
				searchKeywordService.saveKeyword(new SearchRequest(query));
				latch.countDown();
			});
		}
		latch.await();

		// then
		HashOperations<String, String, String> hashOperations = redisHashTemplate.opsForHash();
		Map<String, String> entries = hashOperations.entries(RedisCacheKey.KEYWORD_HITS);
		int actual = Integer.parseInt(entries.get(query));
		assertThat(actual).isEqualTo(100);
	}
}
