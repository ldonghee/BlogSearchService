package com.dhlee.blogsearch.domain;

import java.util.List;

import org.springframework.web.reactive.function.client.WebClient;

import com.dhlee.blogsearch.model.SearchRequest;
import com.dhlee.blogsearch.model.SearchResult;

public interface SearchEngine {
	List<SearchResult> search(WebClient webClient, SearchRequest searchRequest);
}
