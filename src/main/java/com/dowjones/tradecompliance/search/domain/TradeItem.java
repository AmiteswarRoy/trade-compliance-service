package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * @Description TradeItem domain POJO
 * @author Infosys
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Component
public class TradeItem {
	private String regulation_id;
	private String regulation_short_code;
	private String item_code;
	private String item_description;
	private String match_phrase;
	private String match_phrase_type;
	private String region_restriction_type;
	private String region_restriction_names;
	private String region_restriction_iso_2;
	private String region_restriction_sources;
	private String goods_codes_1;
	private String goods_codes_2;

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

	/*public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocUri() {
		return docUri;
	}

	public void setDocUri(String docUri) {
		this.docUri = docUri;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getPrivacy() {
		return privacy;
	}

	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBibAuthor() {
		return bibAuthor;
	}

	public void setBibAuthor(String bibAuthor) {
		this.bibAuthor = bibAuthor;
	}

	public String getBibTitle() {
		return bibTitle;
	}

	public void setBibTitle(String bibTitle) {
		this.bibTitle = bibTitle;
	}

	public String getBibDescription() {
		return bibDescription;
	}

	public void setBibDescription(String bibDescription) {
		this.bibDescription = bibDescription;
	}

	public Integer getBibPageCount() {
		return bibPageCount;
	}

	public void setBibPageCount(Integer bibPageCount) {
		this.bibPageCount = bibPageCount;
	}

	public List<String> getSystemTags() {
		return systemTags;
	}

	public void setSystemTags(List<String> systemTags) {
		this.systemTags = systemTags;
	}

	public List<String> getCustomTags() {
		return customTags;
	}

	public void setCustomTags(List<String> customTags) {
		this.customTags = customTags;
	}

	public String getIeeePrivacy() {
		return ieeePrivacy;
	}

	public void setIeeePrivacy(String ieeePrivacy) {
		this.ieeePrivacy = ieeePrivacy;
	}

	public String getNonIeeeCopyRight() {
		return nonIeeeCopyRight;
	}

	public void setNonIeeeCopyRight(String nonIeeeCopyRight) {
		this.nonIeeeCopyRight = nonIeeeCopyRight;
	}

	public Boolean getDownloadable() {
		return downloadable;
	}

	public void setDownloadable(Boolean downloadable) {
		this.downloadable = downloadable;
	}

	public Boolean getViewable() {
		return viewable;
	}

	public void setViewable(Boolean viewable) {
		this.viewable = viewable;
	}*/

}
