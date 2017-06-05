package com.dowjones.tradecompliance.search.util;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dowjones.tradecompliance.search.domain.BaseResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BuildResponseUtil {
	private static Gson gson = new GsonBuilder().create();
	
	public static ResponseEntity<?> createSuccessfulResponse(String message) {
		return createSuccessfulResponse(new BaseResponse(message), HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(String message, HttpStatus status) {
		return createSuccessfulResponse(new BaseResponse(message), status);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(BaseResponse response) {
		return createSuccessfulResponse(response, HttpStatus.OK);
	}
	
	public static ResponseEntity<?> createSuccessfulResponse(BaseResponse response, HttpStatus status) {
		//String responseJson = gson.toJson(response);
		return new ResponseEntity(response, status);
	}
	
	public static ResponseEntity<?> createErrorResponse(String errorCode, String errorMessage, HttpStatus status) {
		return new ResponseEntity(new BaseResponse(errorCode, errorMessage), status);
	}
	
	public static ResponseEntity<?> createErrorResponse(String errorCode, List<String> errorMessages, HttpStatus status) {
		return new ResponseEntity(new BaseResponse(errorCode, errorMessages), status);
	}

}
