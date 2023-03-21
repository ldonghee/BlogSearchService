package com.dhlee.blogsearch.search.domain;

import com.dhlee.blogsearch.search.exception.InvalidArgumentException;
import com.dhlee.blogsearch.search.model.SearchRequest;

public interface SearchParam {
	default int getPage(int page) {
		if (page == 0) {
			return 1;
		}
		if (page > 50) {
			throw new InvalidArgumentException();
		}
		return page;
	}

	default int getSize(int size) {
		if (size == 0) {
			return 10;
		}
		if (size > 50) {
			throw new InvalidArgumentException();
		}
		return size;
	}

	String getQueryParameters(SearchRequest searchRequestParam);
}
