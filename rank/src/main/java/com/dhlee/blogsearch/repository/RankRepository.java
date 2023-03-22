package com.dhlee.blogsearch.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhlee.blogsearch.domain.Keyword;

@Repository
public interface RankRepository extends JpaRepository<Keyword, Long> {
	Optional<Keyword> findByQuery(String query);
	List<Keyword> findTop10ByOrderByCountDesc();
}
