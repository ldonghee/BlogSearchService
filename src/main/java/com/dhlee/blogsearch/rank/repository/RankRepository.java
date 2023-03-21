package com.dhlee.blogsearch.rank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.dhlee.blogsearch.rank.domain.Keyword;

@Repository
public interface RankRepository extends JpaRepository<Keyword, Long> {
	@Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
	Optional<Keyword> findByQuery(String query);
	List<Keyword> findTop10ByOrderByCountDesc();
}
