package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ResponseResult {
	
	private MetaData meta;
	private List<DataEntity> data;
	
	public MetaData getMeta() {
		return meta;
	}
	
	public void setMeta(MetaData meta) {
		this.meta = meta;
	}
	
	public List<DataEntity> getData() {
		return data;
	}
	
	public void setData(List<DataEntity> data) {
		this.data = data;
	}

}
