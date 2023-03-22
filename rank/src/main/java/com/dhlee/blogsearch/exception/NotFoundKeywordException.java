package com.dhlee.blogsearch.exception;

public class NotFoundKeywordException extends BusinessException {
	public NotFoundKeywordException() {
		super("존재하지 않는 keyword 입니다.");
	}
}
