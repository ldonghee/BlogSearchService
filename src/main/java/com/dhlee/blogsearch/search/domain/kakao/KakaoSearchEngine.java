package com.dhlee.blogsearch.search.domain.kakao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.search.domain.SearchEngine;
import com.dhlee.blogsearch.search.domain.SearchParam;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

public class KakaoSearchEngine implements SearchEngine {
	private static final String BLOG_SEARCH_BASE_URL = "https://dapi.kakao.com/v2/search/blog";
	private static final String ELEMENT_NAME = "documents";
	private static final String HEADER_AUTHORIZATION_KEY = "Authorization";
	private static final String HEADER_AUTHORIZATION_VALUE = "KakaoAK ";
	private static final String REST_API_KEY = "5b173ff11c3ab87902301c762eff4b1e";

	private final SearchParam searchParam;

	public KakaoSearchEngine(SearchParam searchParam) {
		this.searchParam = searchParam;
	}


	@Override
	public List<SearchResult> search(WebClient webClient, SearchRequest searchRequest) {
		String queryParameters = searchParam.getQueryParameters(searchRequest);

		Map result = webClient.get()
							  .uri(BLOG_SEARCH_BASE_URL + queryParameters)
							  .header(HEADER_AUTHORIZATION_KEY, HEADER_AUTHORIZATION_VALUE + REST_API_KEY)
							  .retrieve()
							  .bodyToMono(Map.class)
							  .block();

		return getResult((List<Map<String, Object>>) result.get(ELEMENT_NAME));
	}

	private List<SearchResult> getResult(List<Map<String, Object>> results) {
		List<SearchResult> list = new ArrayList<>();

		for(Map<String, Object> result : results){
			list.add(new KakaoSearchResult(result).toSearchResult());
		}

		return list;
	}
}
