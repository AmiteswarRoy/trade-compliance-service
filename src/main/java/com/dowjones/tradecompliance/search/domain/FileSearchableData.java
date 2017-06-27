package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FileSearchableData {

	@Valid
	private List<SearchDataCriteria> data;
	
	public List<SearchDataCriteria> getData() {
		return data;
	}
	
	public void setData(List<SearchDataCriteria> data) {
		this.data = data;
	}
}
