package com.dowjones.tradecompliance.search.domain;

/**
 * 
 * @Description Response POJO for Item creation
 * @author Infosys
 *
 */
public class ItemResponse {
	private String message;
	
	public ItemResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public ItemResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
