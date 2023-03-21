package com.dhlee.blogsearch.search.domain.naver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.search.domain.SearchEngine;
import com.dhlee.blogsearch.search.domain.SearchParam;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

public class NaverSearchEngine implements SearchEngine {
	private static final String BLOG_SEARCH_BASE_URL = "https://openapi.naver.com/v1/search/blog";
	private static final String ELEMENT_NAME = "items";
	private static final String HEADER_CLIENT_ID_KEY = "X-Naver-Client-Id";
	private static final String HEADER_CLIENT_SECRET_KEY = "X-Naver-Client-Secret";
	private static final String HEADER_CLIENT_ID_VALUE = "mjHmQQbiUKZAfUmIFM4h";
	private static final String HEADER_CLIENT_SECRET_VALUE = "y5PcMGrK8c";

	private final SearchParam searchParam;

	public NaverSearchEngine(SearchParam searchParam) {
		this.searchParam = searchParam;
	}


	@Override
	public List<SearchResult> search(WebClient webClient, SearchRequest searchRequest) {
		String queryParameters = searchParam.getQueryParameters(searchRequest);

		Map result = webClient.get()
							  .uri(BLOG_SEARCH_BASE_URL + queryParameters)
							  .header(HEADER_CLIENT_ID_KEY, HEADER_CLIENT_ID_VALUE)
							  .header(HEADER_CLIENT_SECRET_KEY, HEADER_CLIENT_SECRET_VALUE)
							  .retrieve()
							  .bodyToMono(Map.class)
							  .block();

		return getResult((List<Map<String, Object>>) result.get(ELEMENT_NAME));
	}

	private List<SearchResult> getResult(List<Map<String, Object>> results) {
		List<SearchResult> list = new ArrayList<>();

		for(Map<String, Object> result : results){
			list.add(new NaverSearchResult(result).toSearchResult());
		}

		return list;
	}
}
