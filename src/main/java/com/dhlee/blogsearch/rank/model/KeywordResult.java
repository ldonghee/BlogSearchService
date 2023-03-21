package com.dhlee.blogsearch.rank.model;

import lombok.Data;

import com.dhlee.blogsearch.rank.domain.Keyword;

@Data
public class KeywordResult {
	private Long id;
	private String query;
	private long count;

	private KeywordResult(Long id, String query, long count) {
		this.id = id;
		this.query = query;
		this.count = count;
	}

	public static KeywordResult of(Keyword keyword) {
		return new KeywordResult(keyword.getId(), keyword.getQuery(), keyword.getCount());
	}
}
