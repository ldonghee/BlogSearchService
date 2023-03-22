package com.dhlee.blogsearch.domain.naver;

import java.util.Arrays;

import lombok.NoArgsConstructor;

import com.dhlee.blogsearch.domain.SearchParam;
import com.dhlee.blogsearch.exception.InvalidArgumentException;
import com.dhlee.blogsearch.model.SearchRequest;

@NoArgsConstructor
public class NaverSearchParam implements SearchParam {

	private static final String QUERY_FORMAT = "?query=%s&sort=%s&start=%s&display=%s";

	@Override
	public String getQueryParameters(SearchRequest searchRequest) {
		return String.format(QUERY_FORMAT
				, searchRequest.getQuery()
				, Sort.getLowerName(searchRequest.getSort())
				, getPage(searchRequest.getPage())
				, getSize(searchRequest.getSize()));
	}

	public int getPage(int page) {
		if (page == 0) {
			return 1;
		}
		if (page > 50) {
			throw new InvalidArgumentException();
		}
		return page;
	}

	public int getSize(int size) {
		if (size == 0) {
			return 10;
		}
		if (size > 50) {
			throw new InvalidArgumentException();
		}
		return size;
	}


	private enum Sort {
		SIM,
		DATE;

		public static String getLowerName(String sort) {
			if (sort == null) {
				return SIM.name().toLowerCase();
			}

			return Arrays.stream(values())
						 .filter(s -> s.name().toLowerCase().equals(sort))
						 .findFirst()
						 .orElseThrow(InvalidArgumentException::new)
						 .name().toLowerCase();
		}
	}
}
