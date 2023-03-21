package com.dhlee.blogsearch.search.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dhlee.blogsearch.search.model.SearchRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchKeywordServiceImpl implements SearchKeywordService {
	private static final String FAIL_SAVE_MESSAGE = "키워드를 저장 할 수 없습니다.";

	@Value("${rank.service.url}")
	private String rankServiceUrl;

	private final WebClient webClient;

	@Override
	public void saveKeyword(SearchRequest searchRequest) {
		String url = rankServiceUrl + "?query=" + searchRequest.getQuery();
		try {
			webClient.patch()
					 .uri(url)
					 .retrieve()
					 .bodyToMono(Map.class)
					 .block();
		} catch (Exception e){
			log.error(e.getMessage());
			throw new RuntimeException(FAIL_SAVE_MESSAGE);
		}
	}
}
