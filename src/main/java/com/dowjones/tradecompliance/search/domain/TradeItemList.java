package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import javax.validation.Valid;


import com.fasterxml.jackson.annotation.JsonCreator;

public class TradeItemList{
	
	@Valid
	private List<TradeItem> list;
	
	public TradeItemList(){
		
	}
	
	@JsonCreator
	public TradeItemList(List<TradeItem> list){
		this.list = list;
	}

	public List<TradeItem> getList() {
		return list;
	}

	public void setList(List<TradeItem> list) {
		this.list = list;
	}

	
	

}
