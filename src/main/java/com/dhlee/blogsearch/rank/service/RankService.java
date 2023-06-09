package com.dhlee.blogsearch.rank.service;

import java.util.List;

import com.dhlee.blogsearch.rank.model.KeywordResult;

public interface RankService {
	KeywordResult saveKeyword(String keyword);

	List<KeywordResult> getRankKeywordList();

	KeywordResult getKeyword(Long id);
}
