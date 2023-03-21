package com.dhlee.blogsearch.search.exception;

import com.dhlee.blogsearch.common.exception.BusinessException;

public class InvalidArgumentException extends BusinessException {
	public InvalidArgumentException() {
		super("유효하지 않는 파라미터 입니다.");
	}
}
