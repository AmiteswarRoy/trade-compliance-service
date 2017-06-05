package com.dowjones.tradecompliance.search.domain;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Component
public class FileData {
	private String itemCode;
	private String itemDescription;
	private String matchPhrase;
	private String goodsCodes1;
	private String goodsCodes2;
	private String goodsCodes3;
	private String goods;
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	public String getMatchPhrase() {
		return matchPhrase;
	}
	public void setMatchPhrase(String matchPhrase) {
		this.matchPhrase = matchPhrase;
	}
	public String getGoodsCodes1() {
		return goodsCodes1;
	}
	public void setGoodsCodes1(String goodsCodes1) {
		this.goodsCodes1 = goodsCodes1;
	}
	public String getGoodsCodes2() {
		return goodsCodes2;
	}
	public void setGoodsCodes2(String goodsCodes2) {
		this.goodsCodes2 = goodsCodes2;
	}
	public String getGoodsCodes3() {
		return goodsCodes3;
	}
	public void setGoodsCodes3(String goodsCodes3) {
		this.goodsCodes3 = goodsCodes3;
	}
	public String getGoods() {
		return goods;
	}
	public void setGoods(String goods) {
		this.goods = goods;
	}
	
}
