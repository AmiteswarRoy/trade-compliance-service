package com.dowjones.tradecompliance.search.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class FileData {
	private String item_code;
	private String item_description;
	private String match_phrase;
	private GoodsCodes codes;
	
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
	public GoodsCodes getCodes() {
		return codes;
	}
	public void setCodes(GoodsCodes codes) {
		this.codes = codes;
	}
	
}
