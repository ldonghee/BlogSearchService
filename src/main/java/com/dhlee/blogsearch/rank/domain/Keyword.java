package com.dhlee.blogsearch.rank.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Getter;

import com.dhlee.blogsearch.common.domain.BaseEntity;

@Getter
@Entity
public class Keyword extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String query;
	private long count;

	@Version
	private Long version;

	public Keyword() {
	}

	public Keyword(String keyword) {
		this.query = keyword;
		this.count = 0;
	}

	public void increase() {
		this.count += 1;
	}
}
