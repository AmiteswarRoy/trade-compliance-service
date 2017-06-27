package com.dowjones.tradecompliance.search.domain;

import java.util.List;

public class ErrorResponse {
	private List<ErrorEntity> errors;

	public List<ErrorEntity> getErrors() {
		return errors;
	}
	
	public void setErrors(List<ErrorEntity> errors) {
		this.errors = errors;
	}

}
