package com.dhlee.blogsearch.model;

import lombok.Data;

@Data
public class SearchRequest {
	private String query;
	private String sort;
	private int page;
	private int size;

	private SearchRequest() {
	}

	public SearchRequest(String query) {
		this.query = query;
	}

	public SearchRequest(String query, String sort) {
		this.query = query;
		this.sort = sort;
	}

	public SearchRequest(String query, String sort, int page, int size) {
		this.query = query;
		this.sort = sort;
		this.page = page;
		this.size = size;
	}

	public SearchRequest(String query, String sort, int page) {
		this.query = query;
		this.sort = sort;
		this.page = page;
	}
}
