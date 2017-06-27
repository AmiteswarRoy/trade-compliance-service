package com.dowjones.tradecompliance.search.domain;

import javax.validation.Valid;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FilterData {
	
	@Valid
	private SearchableGoods filters;

	public SearchableGoods getFilters() {
		return filters;
	}
	
	public void setFilters(SearchableGoods filters) {
		this.filters = filters;
	}
	
}
