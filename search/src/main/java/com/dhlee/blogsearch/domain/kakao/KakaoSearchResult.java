package com.dhlee.blogsearch.domain.kakao;

import java.util.Map;

import com.dhlee.blogsearch.model.SearchResult;

public class KakaoSearchResult {
	private static final String KEY_TITLE = "title";
	private static final String KEY_CONTENTS = "contents";
	private static final String KEY_URL = "url";
	private static final String KEY_DATETIME = "datetime";
	private static final String KEY_BLOGNAME = "blogname";

	private String title;
	private String contents;
	private String url;
	private String datetime;
	private String blogName;

	public KakaoSearchResult(Map<String, Object> data) {
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
