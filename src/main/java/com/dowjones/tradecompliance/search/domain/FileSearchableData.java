package com.dowjones.tradecompliance.search.domain;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FileSearchableData {

	@Valid
	private SearchCriteria criteria;
	
	public SearchCriteria getCriteria() {
		return criteria;
	}
	
	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}
}
