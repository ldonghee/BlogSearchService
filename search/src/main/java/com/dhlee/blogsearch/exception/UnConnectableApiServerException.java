package com.dhlee.blogsearch.exception;

public class UnConnectableApiServerException extends BusinessException {
	public UnConnectableApiServerException() {
		super("현재 연결 가능한 외부 API 서버가 없습니다.");
	}
}
