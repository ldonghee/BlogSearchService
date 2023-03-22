package com.dhlee.blogsearch.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.dhlee.blogsearch.domain.Keyword;
import com.dhlee.blogsearch.exception.NotFoundKeywordException;
import com.dhlee.blogsearch.model.KeywordResult;
import com.dhlee.blogsearch.repository.RankRepository;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService {

	private final RankRepository rankRepository;

	@Override
	public KeywordResult getKeyword(Long id) {
		Keyword keyword = rankRepository.findById(id)
										.orElseThrow(NotFoundKeywordException::new);
		return KeywordResult.of(keyword);
	}

	@Override
	public List<KeywordResult> getRankKeywordList() {
		List<Keyword> rankKeywordList = rankRepository.findTop10ByOrderByCountDesc();
		return rankKeywordList.stream()
					   .map(KeywordResult::of)
					   .collect(Collectors.toList());
	}

	@Override
	@Transactional
	public KeywordResult saveKeyword(String query) {
		Keyword keyword = rankRepository.findByQuery(query)
										.orElse(new Keyword(query));
		keyword.increase();
		return KeywordResult.of(rankRepository.save(keyword));
	}
}
