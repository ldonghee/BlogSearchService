package com.dhlee.blogsearch.rank.exception;

import com.dhlee.blogsearch.common.exception.BusinessException;

public class NotFoundKeywordException extends BusinessException {
	public NotFoundKeywordException() {
		super("존재하지 않는 keyword 입니다.");
	}
}
