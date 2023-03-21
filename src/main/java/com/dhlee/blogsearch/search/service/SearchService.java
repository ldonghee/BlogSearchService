package com.dhlee.blogsearch.search.service;

import java.util.List;

import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;


public interface SearchService {
	List<SearchResult> getBlogList(SearchRequest searchRequestParam);
}
