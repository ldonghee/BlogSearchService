package com.dhlee.blogsearch.search.service;

import com.dhlee.blogsearch.search.model.SearchRequest;

public interface SearchKeywordService {
	void saveKeyword(SearchRequest searchRequest);
}
