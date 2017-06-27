package com.dowjones.tradecompliance.search.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class GoodsCodes {

	private String goods_codes_1;
	private String goods_codes_2;
	private String goods_codes_3;
	
	public String getGoods_codes_1() {
		return goods_codes_1;
	}
	
	public void setGoods_codes_1(String goods_codes_1) {
		this.goods_codes_1 = goods_codes_1;
	}
	
	public String getGoods_codes_2() {
		return goods_codes_2;
	}
	
	public void setGoods_codes_2(String goods_codes_2) {
		this.goods_codes_2 = goods_codes_2;
	}
	
	public String getGoods_codes_3() {
		return goods_codes_3;
	}
	
	public void setGoods_codes_3(String goods_codes_3) {
		this.goods_codes_3 = goods_codes_3;
	}
}
