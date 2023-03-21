package com.dhlee.blogsearch.search.domain;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

import com.dhlee.blogsearch.search.domain.kakao.KakaoSearchEngine;
import com.dhlee.blogsearch.search.domain.kakao.KakaoSearchParam;
import com.dhlee.blogsearch.search.domain.naver.NaverSearchEngine;
import com.dhlee.blogsearch.search.domain.naver.NaverSearchParam;
import com.dhlee.blogsearch.search.exception.UnConnectableApiServerException;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

@Slf4j
@Component
public class SearchEngineManager {
	private final List<SearchEngine> searchEngineList = new LinkedList<>();
	private final WebClient webClient;


	public SearchEngineManager(WebClient webClient) {
		this.webClient = webClient;
		this.searchEngineList.add(new KakaoSearchEngine(new KakaoSearchParam()));
		this.searchEngineList.add(new NaverSearchEngine(new NaverSearchParam()));
	}

	public List<SearchResult> search(SearchRequest searchRequest) {
		for (SearchEngine searchEngine : searchEngineList) {
			try {
				return searchEngine.search(webClient, searchRequest);
			} catch (RuntimeException e) {
				log.error(e.getMessage());
			}
		}

		throw new UnConnectableApiServerException();
	}
}
