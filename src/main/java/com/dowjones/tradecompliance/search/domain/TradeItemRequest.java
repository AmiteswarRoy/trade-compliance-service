package com.dowjones.tradecompliance.search.domain;

import javax.validation.Valid;

public class TradeItemRequest {
	private String type;
	@Valid
	private TradeItem attributes;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public TradeItem getAttributes() {
		return attributes;
	}
	public void setAttributes(TradeItem attributes) {
		this.attributes = attributes;
	}
}
