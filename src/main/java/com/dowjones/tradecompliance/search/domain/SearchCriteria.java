package com.dowjones.tradecompliance.search.domain;

import java.io.Serializable;
import java.util.List;

public class SearchCriteria implements Serializable {
	
private List<String> goods;
	
	public List<String> getGoods() {
		return goods;
	}
	
	public void setGoods(List<String> goods) {
		this.goods = goods;
	}

}
