package com.dowjones.tradecompliance.search.domain;

/**
 * 
 * @Description Response POJO for Item creation
 * @author Infosys
 *
 */
public class ItemCreationResponse {
	private String status;
	private String message;
	private int responseCode;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	
	

}
