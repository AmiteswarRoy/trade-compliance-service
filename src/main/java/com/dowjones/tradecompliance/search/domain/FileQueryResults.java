package com.dowjones.tradecompliance.search.domain;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Component
public class FileQueryResults extends BaseResponse {
	private Integer totalHits;
	private List<FileData> files;
	
	public FileQueryResults() { }
	
	public Integer getTotalHits() {
		return totalHits;
	}
	public void setTotalHits(Integer totalHits) {
		this.totalHits = totalHits;
	}
	
	public List<FileData> getFiles() {
		return files;
	}
	public void setFiles(List<FileData> files) {
		this.files = files;
	}

}
