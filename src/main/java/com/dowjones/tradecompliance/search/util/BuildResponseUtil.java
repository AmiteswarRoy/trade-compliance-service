package com.dowjones.tradecompliance.search.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dowjones.tradecompliance.search.domain.ItemResponse;

@SuppressWarnings("rawtypes")
public class BuildResponseUtil {
	
	public static ResponseEntity<?> createSuccessfulResponse(String message) {
		return createSuccessfulResponse(new ItemResponse(message), HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(String message, HttpStatus status) {
		return createSuccessfulResponse(new ItemResponse(message), status);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(ItemResponse response) {
		return createSuccessfulResponse(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(ItemResponse response, HttpStatus status) {
		return new ResponseEntity(response, status);
	}
	
	public static ResponseEntity<?> createErrorResponse( String errorMessage, HttpStatus status) {
		return new ResponseEntity(new ItemResponse(errorMessage), status);
	}
	
}
