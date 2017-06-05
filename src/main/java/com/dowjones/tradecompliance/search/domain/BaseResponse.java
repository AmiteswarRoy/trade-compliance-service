package com.dowjones.tradecompliance.search.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BaseResponse {
	private String message;
	private String errorCode;
	private List<String> errorMessages;
	
	public BaseResponse() {}
	
	public BaseResponse(String message) {
		super();
		this.message = message;
	}

	public BaseResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessages = new ArrayList<String>();
		this.errorMessages.add(errorMessage);
	}

	public BaseResponse(String errorCode, List<String> errorMessages) {
		super();
		this.errorCode = errorCode;
		this.errorMessages = new ArrayList<String>(errorMessages);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	@Override
	public String toString() {
		return "BaseResponse [message=" + message + ", errorCode=" + errorCode + ", errorMessages=" + errorMessages
				+ "]";
	}
}
