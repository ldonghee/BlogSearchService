package com.dhlee.blogsearch.search.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.dhlee.blogsearch.common.model.CommonResponse;
import com.dhlee.blogsearch.search.model.SearchRequest;
import com.dhlee.blogsearch.search.model.SearchResult;
import com.dhlee.blogsearch.search.service.SearchKeywordService;
import com.dhlee.blogsearch.search.service.SearchService;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class SearchController {
	private final SearchService searchService;
	private final SearchKeywordService searchKeywordService;

	@GetMapping("/blog")
	public ResponseEntity<CommonResponse<List<SearchResult>>> getBlogList(SearchRequest searchRequestParam) {
		searchKeywordService.saveKeyword(searchRequestParam);
		List<SearchResult> blogList = searchService.getBlogList(searchRequestParam);
		return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), blogList));
	}
}
