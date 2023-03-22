package com.dhlee.blogsearch.rank.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;

import com.dhlee.blogsearch.common.domain.BaseEntity;

@Getter
@Entity
public class Keyword extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 100)
	private String query;
	private long count;

	public Keyword() {
	}

	public Keyword(String keyword) {
		this.query = keyword;
		this.count = 0;
	}

	public Keyword(String keyword, Integer count) {
		this.query = keyword;
		this.count = count;
	}

	public void increase() {
		this.count += 1;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
