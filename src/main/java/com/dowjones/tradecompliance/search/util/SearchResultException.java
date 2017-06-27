package com.dowjones.tradecompliance.search.util;

public class SearchResultException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9172214907218820108L;
	private int responseCode;
	private String errorMessage;
	
	public SearchResultException(int responseCode, String errorMessage) {
		super();
		this.responseCode = responseCode;
		this.errorMessage = errorMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
