package com.dowjones.tradecompliance.search.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dowjones.tradecompliance.search.domain.ItemResponse;

public class BuildResponseUtil {
	
	public static ResponseEntity<?> createSuccessfulResponse(String message) {
		return createSuccessfulResponse(new ItemResponse().getMessage(), HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(String message, HttpStatus status) {
		return createSuccessfulResponse(new ItemResponse().getMessage(), status);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(ItemResponse response) {
		return createSuccessfulResponse(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(ItemResponse response, HttpStatus status) {
		//String responseJson = gson.toJson(response);
		return new ResponseEntity(response, status);
	}
	
	public static ResponseEntity<?> createErrorResponse(int errorCode, String errorMessage, HttpStatus status) {
		return new ResponseEntity(new ItemResponse(errorMessage, errorCode), status);
	}
	
}
