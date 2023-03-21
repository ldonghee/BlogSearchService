package com.dhlee.blogsearch.rank.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.dhlee.blogsearch.common.model.CommonResponse;
import com.dhlee.blogsearch.rank.model.KeywordResult;
import com.dhlee.blogsearch.rank.service.RankService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/rank")
public class RankController {

	private final RankService rankService;

	@GetMapping("/keywordList")
	public ResponseEntity<CommonResponse<List<KeywordResult>>> getRankKeywordList() {
		return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), rankService.getRankKeywordList()));
	}

	@GetMapping("/keyword/{id}")
	public ResponseEntity<CommonResponse<KeywordResult>> getKeyword(@PathVariable Long id) {
		return ResponseEntity.ok(new CommonResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), rankService.getKeyword(id)));
	}

	@PatchMapping("/keyword")
	public ResponseEntity<CommonResponse<KeywordResult>> saveKeyword(String query) {
		KeywordResult response = rankService.saveKeyword(query);
		return ResponseEntity.created(URI.create("/rank/keyword/" + response.getId()))
							 .body(new CommonResponse<>(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), response));
	}
}
