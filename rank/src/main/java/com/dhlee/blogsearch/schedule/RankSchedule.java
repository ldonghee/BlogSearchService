package com.dhlee.blogsearch.schedule;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.blogsearch.config.redis.RedisCacheKey;
import com.dhlee.blogsearch.domain.Keyword;
import com.dhlee.blogsearch.repository.RankRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class RankSchedule {

	private final RedisTemplate<String, Integer> redisHashTemplate;
	private final RankRepository rankRepository;

	@Scheduled(cron = "*/5 * * * * *")
	public void RedisToDatabaseTask() {
		HashOperations<String, String, String> hashOperations = redisHashTemplate.opsForHash();
		Map<String, String> entries = hashOperations.entries(RedisCacheKey.KEYWORD_HITS);

		log.info("Schedule Start");
		for (String key : entries.keySet()) {
			saveKeyword(key, Integer.parseInt(entries.get(key)));
		}
	}

	@Transactional
	void saveKeyword(String key, Integer count) {
		log.info("Key : " + key + ", Count : " + count);
		Keyword keyword = rankRepository.findByQuery(key)
										.orElse(new Keyword(key, count));
		keyword.setCount(count);
		rankRepository.save(keyword);
	}
}
