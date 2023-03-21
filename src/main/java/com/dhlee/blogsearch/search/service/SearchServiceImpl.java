package com.dhlee.blogsearch.search.service;


import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.dhlee.blogsearch.search.domain.SearchEngineManager;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

	private final SearchEngineManager searchEngineManager;

	@Override
	@Cacheable(value="blogList", key="#searchRequest.query + '_' + #searchRequest.sort + '_' + #searchRequest.page + '_' + #searchRequest.size")
	public List<SearchResult> getBlogList(SearchRequest searchRequest) {
		return searchEngineManager.search(searchRequest);
	}
}
