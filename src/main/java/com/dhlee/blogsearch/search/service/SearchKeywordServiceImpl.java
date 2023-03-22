package com.dhlee.blogsearch.search.service;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.blogsearch.common.config.redis.RedisCacheKey;
import com.dhlee.blogsearch.search.model.SearchRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchKeywordServiceImpl implements SearchKeywordService {

	private final RedisTemplate<String, Integer> redisHashTemplate;

	@Override
	public void saveKeyword(SearchRequest searchRequest) {
		HashOperations<String, String, Integer> hashOperations = redisHashTemplate.opsForHash();
		hashOperations.increment(RedisCacheKey.KEYWORD_HITS, searchRequest.getQuery(), 1);
	}
}
