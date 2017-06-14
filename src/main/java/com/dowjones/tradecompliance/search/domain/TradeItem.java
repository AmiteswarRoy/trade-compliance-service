package com.dowjones.tradecompliance.search.domain;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;
/**
 * @Description TradeItem domain POJO
 * @author Infosys
 *
 */
@Component
public class TradeItem {
	
	@NotNull(message="regulation_id is required field")
	private String regulation_id;
	
	@NotNull(message="regulation_short_code is required field")
	private String regulation_short_code;
	
	@NotNull(message="item_code is required field")
	private String item_code;
	
	@NotNull(message="item_description is required field")
	private String item_description;
	
	@NotNull(message="match_phrase is required field")
	private String match_phrase;
	
	@NotNull(message="match_phrase_type is required field")
	private String match_phrase_type;
	
	@NotNull(message="region_restriction_type is required field")
	private String region_restriction_type;
	
	@NotNull(message="region_restriction_names is required field")
	private String region_restriction_names;
	
	@NotNull(message="region_restriction_iso_2 is required field")
	private String region_restriction_iso_2;
	
	@NotNull(message="region_restriction_sources is required field")
	private String region_restriction_sources;
	
	@NotNull(message="goods_codes_1 is required field")
	private String goods_codes_1;
	
	@NotNull(message="goods_codes_2 is required field")
	private String goods_codes_2;
	
	@NotNull(message="goods_codes_3 is required field")
	private String goods_codes_3;
	
	@NotNull(message="goods is required field")
	private String goods;

	public TradeItem() { }
	
	public String getRegulation_id() {
		return regulation_id;
	}

	public void setRegulation_id(String regulation_id) {
		this.regulation_id = regulation_id;
	}

	public String getRegulation_short_code() {
		return regulation_short_code;
	}

	public void setRegulation_short_code(String regulation_short_code) {
		this.regulation_short_code = regulation_short_code;
	}

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

	public String getMatch_phrase_type() {
		return match_phrase_type;
	}

	public void setMatch_phrase_type(String match_phrase_type) {
		this.match_phrase_type = match_phrase_type;
	}

	public String getRegion_restriction_type() {
		return region_restriction_type;
	}

	public void setRegion_restriction_type(String region_restriction_type) {
		this.region_restriction_type = region_restriction_type;
	}

	public String getRegion_restriction_names() {
		return region_restriction_names;
	}

	public void setRegion_restriction_names(String region_restriction_names) {
		this.region_restriction_names = region_restriction_names;
	}

	public String getRegion_restriction_iso_2() {
		return region_restriction_iso_2;
	}

	public void setRegion_restriction_iso_2(String region_restriction_iso_2) {
		this.region_restriction_iso_2 = region_restriction_iso_2;
	}

	public String getRegion_restriction_sources() {
		return region_restriction_sources;
	}

	public void setRegion_restriction_sources(String region_restriction_sources) {
		this.region_restriction_sources = region_restriction_sources;
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
	
	public String getGoods() {
		return goods;
	}
	
	public void setGoods(String goods) {
		this.goods = goods;
	}
}
