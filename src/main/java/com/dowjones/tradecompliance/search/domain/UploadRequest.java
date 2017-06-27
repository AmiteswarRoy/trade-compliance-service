package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import javax.validation.Valid;

public class UploadRequest {
	@Valid
	private List<TradeItemRequest> data;

	public List<TradeItemRequest> getData() {
		return data;
	}

	public void setData(List<TradeItemRequest> data) {
		this.data = data;
	}

}
