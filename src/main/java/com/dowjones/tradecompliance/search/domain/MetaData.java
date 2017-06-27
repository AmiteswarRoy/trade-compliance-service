package com.dowjones.tradecompliance.search.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class MetaData {

	private Integer hits;
	
	public Integer getHits() {
		return hits;
	}
	
	public void setHits(Integer hits) {
		this.hits = hits;
	}
}
