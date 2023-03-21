package com.dhlee.blogsearch.search.domain;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

public interface SearchEngine {
	List<SearchResult> search(WebClient webClient, SearchRequest searchRequest);
}
