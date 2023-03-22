package com.dhlee.blogsearch.service;

import java.util.List;

import com.dhlee.blogsearch.model.KeywordResult;

public interface RankService {
	KeywordResult saveKeyword(String keyword);

	List<KeywordResult> getRankKeywordList();

	KeywordResult getKeyword(Long id);
}
