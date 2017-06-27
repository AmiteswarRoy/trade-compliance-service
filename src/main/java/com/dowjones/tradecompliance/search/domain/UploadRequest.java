package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import javax.validation.Valid;

public class UploadRequest {
	@Valid
	private List<TradeItem> data;

	public List<TradeItem> getData() {
		return data;
	}

	public void setData(List<TradeItem> data) {
		this.data = data;
	}

}
