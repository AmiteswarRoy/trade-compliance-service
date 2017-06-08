package com.dowjones.tradecompliance.search.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SearchCriteria {
	
private List<String> goods;
	
	public List<String> getGoods() {
		return goods;
	}
	
	public void setGoods(List<String> goods) {
		this.goods = goods;
	}

}
