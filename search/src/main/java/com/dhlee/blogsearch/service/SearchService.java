package com.dhlee.blogsearch.service;

import java.util.List;

import com.dhlee.blogsearch.model.SearchRequest;
import com.dhlee.blogsearch.model.SearchResult;


public interface SearchService {
	List<SearchResult> getBlogList(SearchRequest searchRequestParam);
}
