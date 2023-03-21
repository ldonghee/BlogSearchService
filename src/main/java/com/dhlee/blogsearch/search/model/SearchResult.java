package com.dhlee.blogsearch.search.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchResult {
	private String title;
	private String url;
	private String contents;
	private String blogName;
	private String datetime;

	public SearchResult(String title, String contents, String url, String datetime, String blogName) {
		this.title = title;
		this.url = url;
		this.contents = contents;
		this.blogName = blogName;
		this.datetime = datetime;
	}
}
