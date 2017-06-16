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
	
	@NotNull(message="region_restriction_type is required field")
	private String region_restriction_type;
	
	private String match_phrase_type;
	private String region_restriction_names;
	private String region_restriction_iso_2;
	private String region_restriction_sources;
	private String goods_codes_1;
	private String goods_codes_2;
	private String goods_codes_3;
	private String dow_jones_id;
	private String synonyms;
	private String additional_notes;
	private String feedback_score;

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

	public String getDow_jones_id() {
		return dow_jones_id;
	}

	public void setDow_jones_id(String dow_jones_id) {
		this.dow_jones_id = dow_jones_id;
	}

	public String getSynonyms() {
		return synonyms;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}

	public String getAdditional_notes() {
		return additional_notes;
	}

	public void setAdditional_notes(String additional_notes) {
		this.additional_notes = additional_notes;
	}

	public String getFeedback_score() {
		return feedback_score;
	}

	public void setFeedback_score(String feedback_score) {
		this.feedback_score = feedback_score;
	}
	
}
