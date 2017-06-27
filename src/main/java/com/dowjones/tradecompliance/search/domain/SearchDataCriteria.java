package com.dowjones.tradecompliance.search.domain;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import com.dowjones.tradecompliance.search.util.ItemConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SearchDataCriteria {
	
	@NotEmpty(message=ItemConstants.TYPE_EMPTY)
	private String type;
	
	@Valid
	private FilterData attributes;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public FilterData getAttributes() {
		return attributes;
	}
	
	public void setAttributes(FilterData attributes) {
		this.attributes = attributes;
	}
	
}
