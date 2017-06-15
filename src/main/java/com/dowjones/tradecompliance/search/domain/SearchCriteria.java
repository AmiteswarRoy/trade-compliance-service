package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.dowjones.tradecompliance.search.util.ItemConstants;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SearchCriteria {

	@NotEmpty(message=ItemConstants.INVALID_INPUT)
	private List<String> goods;
	
	public List<String> getGoods() {
		return goods;
	}
	
	public void setGoods(List<String> goods) {
		this.goods = goods;
	}

}
