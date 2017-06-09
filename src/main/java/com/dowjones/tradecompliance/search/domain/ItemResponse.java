package com.dowjones.tradecompliance.search.domain;

/**
 * 
 * @Description Response POJO for Item creation
 * @author Infosys
 *
 */
public class ItemResponse {
	private String message;
	private Integer responseCode;
	
	public ItemResponse() {
		// TODO Auto-generated constructor stub
	}
	public ItemResponse(String message) {
		this.message = message;
	}
	
	public ItemResponse(String message, int responseCode) {
		this.message = message;
		this.responseCode = responseCode;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	
	
	

}
