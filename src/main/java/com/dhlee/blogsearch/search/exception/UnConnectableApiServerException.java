package com.dhlee.blogsearch.search.exception;

import com.dhlee.blogsearch.common.exception.BusinessException;

public class UnConnectableApiServerException extends BusinessException {
	public UnConnectableApiServerException() {
		super("현재 연결 가능한 외부 API 서버가 없습니다.");
	}
}
