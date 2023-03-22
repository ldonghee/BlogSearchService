package com.dhlee.blogsearch.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.dhlee.blogsearch.config.redis.RedisCacheKey;

@SpringBootTest
public class RankServiceTest {
	private static final int THREAD_COUNT = 1000;
	private static final ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

	@Autowired
	private RedisTemplate redisHashTemplate;

	@Autowired
	private SearchKeywordService searchKeywordService;

	@Test
	@DisplayName("100번의 검색어 동시 접근 시, 횟수 증가 테스트")
	public void concurrency() throws InterruptedException, BrokenBarrierException {
		// given
		String query = "test";

		CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_COUNT + 1);

		// when
		CountDownLatch latch = new CountDownLatch(THREAD_COUNT);
		for (int i=0; i < THREAD_COUNT; i++) {
			service.execute(() -> {
				try {
					cyclicBarrier.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}

				searchKeywordService.saveKeyword(query);
				latch.countDown();
			});
		}

		cyclicBarrier.await();
		latch.await();

		// then
		HashOperations<String, String, String> hashOperations = redisHashTemplate.opsForHash();
		Map<String, String> entries = hashOperations.entries(RedisCacheKey.KEYWORD_HITS);
		int actual = Integer.parseInt(entries.get(query));
		assertThat(actual).isEqualTo(THREAD_COUNT);
	}
}
