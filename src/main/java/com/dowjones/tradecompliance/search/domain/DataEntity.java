package com.dowjones.tradecompliance.search.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class DataEntity {

	private String type;
	private String id;
	private FileData attributes;
	private String relationships;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public FileData getAttributes() {
		return attributes;
	}
	public void setAttributes(FileData attributes) {
		this.attributes = attributes;
	}
	public String getRelationships() {
		return relationships;
	}
	public void setRelationships(String relationships) {
		this.relationships = relationships;
	}
	
	
}
