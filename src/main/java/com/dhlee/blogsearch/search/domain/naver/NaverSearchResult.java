package com.dhlee.blogsearch.search.domain.naver;

import java.util.Map;

import com.dhlee.blogsearch.search.model.SearchResult;

public class NaverSearchResult {
	private static final String KEY_TITLE = "title";
	private static final String KEY_CONTENTS = "description";
	private static final String KEY_URL = "link";
	private static final String KEY_DATETIME = "postdate";
	private static final String KEY_BLOGNAME = "bloggername";

	private String title;
	private String contents;
	private String url;
	private String datetime;
	private String blogName;

	public NaverSearchResult(Map<String, Object> data) {
		this.title = (String) data.get(KEY_TITLE);
		this.contents = (String) data.get(KEY_CONTENTS);
		this.url = (String) data.get(KEY_URL);
		this.datetime = (String) data.get(KEY_DATETIME);
		this.blogName = (String) data.get(KEY_BLOGNAME);
	}

	public SearchResult toSearchResult() {
		return new SearchResult(title, contents, url, datetime, blogName);
	}
}
