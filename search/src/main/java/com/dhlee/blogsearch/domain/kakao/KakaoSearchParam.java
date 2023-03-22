package com.dhlee.blogsearch.domain.kakao;

import java.util.Arrays;

import lombok.NoArgsConstructor;

import com.dhlee.blogsearch.domain.SearchParam;
import com.dhlee.blogsearch.exception.InvalidArgumentException;
import com.dhlee.blogsearch.model.SearchRequest;

@NoArgsConstructor
public class KakaoSearchParam implements SearchParam {
	private static final String QUERY_FORMAT = "?query=%s&sort=%s&page=%s&size=%s";

	@Override
	public String getQueryParameters(SearchRequest searchRequest) {
		return String.format(QUERY_FORMAT
				, searchRequest.getQuery()
				, Sort.getLowerName(searchRequest.getSort())
				, getPage(searchRequest.getPage())
				, getSize(searchRequest.getSize()));
	}

	private enum Sort {
		ACCURACY,
		RECENCY;


		public static String getLowerName(String sort) {
			if (sort == null) {
				return ACCURACY.name().toLowerCase();
			}

			return Arrays.stream(values())
						 .filter(s -> s.name().toLowerCase().equals(sort))
						 .findFirst()
						 .orElseThrow(InvalidArgumentException::new)
						 .name().toLowerCase();
		}
	}
}
