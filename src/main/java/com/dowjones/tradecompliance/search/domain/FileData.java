package com.dowjones.tradecompliance.search.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@Component
public class FileData {
	private String item_code;
	private String item_description;
	private String match_phrase;
	private String goods_codes_1;
	private String goods_codes_2;
	private String goods_codes_3;
	
	public String getItem_code() {
		return item_code;
	}
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	public String getItem_description() {
		return item_description;
	}
	public void setItem_description(String item_description) {
		this.item_description = item_description;
	}
	public String getMatch_phrase() {
		return match_phrase;
	}
	public void setMatch_phrase(String match_phrase) {
		this.match_phrase = match_phrase;
	}
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
