package com.dowjones.tradecompliance.search.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.DataEntity;
import com.dowjones.tradecompliance.search.domain.ErrorEntity;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeComplianceResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.domain.UploadRequest;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.BuildResponseUtil;
import com.dowjones.tradecompliance.search.util.ItemConstants;

/**
 * 
 * @author Infosys
 *
 */
@RestController
@RequestMapping("/tradecompliance")
public class SearchController {
	private static Logger logger = LogManager.getLogger(SearchController.class);

	@Autowired
	@Qualifier("FileSearchService")
	FileSearch fileSearch;

	/**
	 * @Desc - Hello end point to check connectivity
	 * @return
	 */
	@GetMapping("/hello")
	public ResponseEntity hello() {
		logger.debug("In Hello endpoint");
		return BuildResponseUtil.createSuccessfulResponse("Greetings from Search Service");
	}

	/**
	 * @Description - To search the data based on search criteria
	 * @param FileSearchableData
	 *            json
	 * @return ResponseResult json
	 */
	@PostMapping("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseResult searchFiles(@Valid @RequestBody FileSearchableData searchableData,
			Errors errors) {
		ResponseResult results = new ResponseResult();
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			String errorDetails = errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(","));
			results = buildErrorEntity("400", errorDetails, ItemConstants.BAD_REQUEST);
		}
		logger.debug("Inside file search method");
		try {
			results = fileSearch.searchFiles(searchableData);
			logger.debug("File search result hits :" + results.getMeta().getHits());
			return results;
			
		} catch (Exception ex) {
			logger.error("File search failed " + ex);
			results = buildErrorEntity("500", ex.getMessage(), ItemConstants.INTERNAL_SERVER_ERROR);
			return results;
		}
		
		
	}

	/**
	 * @Description - To create trade item in elastic search
	 * @param TradeItem
	 *            json
	 * @return Json Response with creation status
	 */
	@SuppressWarnings("unchecked")
	//@PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ResponseEntity<ItemResponse> createFile(@Valid @RequestBody UploadRequest uploadRequest, Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		logger.debug("File Create Request");
		try {
			if(null != uploadRequest && !CollectionUtils.isEmpty(uploadRequest.getData())){
				TradeItem file = uploadRequest.getData().get(0).getAttributes();
				ItemResponse response = fileSearch.createFile(file);
				if(null != response){
					return (ResponseEntity<ItemResponse>) BuildResponseUtil.createSuccessfulResponse(response);
				}else{
					return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else{
				return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception ex) {
			logger.error("Item creation failure " + ex);
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE +" due to "+ex,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Description - To create multiple trade items in elastic search
	 * @param List
	 *            of trade items in json format
	 * @return Json Response with creation status
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/upload")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody TradeComplianceResponse createBulkFile(@RequestBody @Valid UploadRequest uploadRequest, Errors errors) {
		
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			ErrorEntity  errorEntity = new ErrorEntity();
			errorEntity.setCode("400");
			errorEntity.setStatus(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			buildErrorResponse(errorEntity);
		}
		logger.debug("Bulk Item Create Request");
		System.out.println("uploadReq "+uploadRequest);
		if (null != uploadRequest && null != uploadRequest.getData() && uploadRequest.getData().size() > 0 ) {
			if(null != uploadRequest.getData().get(0)){
				try {
					ItemResponse response = fileSearch.createBulkFiles(uploadRequest.getData());
					if(null != response){
						return buildSuccessResponse(response);
						
					}else{
						ErrorEntity errorEntity = new ErrorEntity();
						errorEntity.setCode("500");
						errorEntity.setStatus(ItemConstants.ITEM_CREATION_FAILURE);
						return buildErrorResponse(errorEntity);
						
					}
					
				} catch (Exception ex) {
					logger.error("Error while creating bulk items" + ex);
					ErrorEntity  errorEntity = new ErrorEntity();
					errorEntity.setCode("500");
					errorEntity.setStatus(ItemConstants.ITEM_CREATION_FAILURE+" due to "+ex);
					return buildErrorResponse(errorEntity);
				}
			}else{
				ErrorEntity  errorEntity = new ErrorEntity();
				errorEntity.setCode("400");
				errorEntity.setStatus(ItemConstants.NO_ITEMS);
				return buildErrorResponse(errorEntity);
				
			}

		}else{
			ErrorEntity  errorEntity = new ErrorEntity();
			errorEntity.setCode("400");
			errorEntity.setStatus(ItemConstants.NO_ITEMS);
			return buildErrorResponse(errorEntity);
		} 

	}

	private TradeComplianceResponse buildErrorResponse(ErrorEntity errorEntity) {
		TradeComplianceResponse uploadResponse = new TradeComplianceResponse();
		uploadResponse.setError(errorEntity);
		return uploadResponse;
	}

	private TradeComplianceResponse buildSuccessResponse(ItemResponse response) {
		TradeComplianceResponse uploadResponse = new TradeComplianceResponse();
		ErrorEntity errorEntity = new ErrorEntity();
		errorEntity.setCode("200");
		errorEntity.setStatus(response.getMessage());
		uploadResponse.setError(errorEntity);
		return uploadResponse;
	} 
	
	/**
	 * @Description - To delete all trade items in elastic search
	 * @param
	 * @return Json Response with creation status
	 */
	@SuppressWarnings("unchecked")
	@DeleteMapping("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody TradeComplianceResponse deleteTradeItems() {
		logger.debug("Delete All Items Request");
		try {
			ItemResponse response = fileSearch.deleteAllItems();
			if(null != response){
				return buildSuccessResponse(response);
			}else{
				ErrorEntity errorEntity = new ErrorEntity();
				errorEntity.setCode("500");
				errorEntity.setStatus(ItemConstants.DELETE_FAILURE);
				return buildErrorResponse(errorEntity);
			}
			
		} catch (Exception ex) {
			logger.error("Error while deleting trade items" + ex);
			ErrorEntity errorEntity = new ErrorEntity();
			errorEntity.setCode("500");
			errorEntity.setStatus(ItemConstants.DELETE_FAILURE+" due to "+ex);
			return buildErrorResponse(errorEntity);
			
		}
	}
	
	/**
	 * @Description - To build the error scenario
	 * @param
	 * @return ResponseResult
	 */
	private ResponseResult buildErrorEntity(String status, String errorDetails, String title) {

		ResponseResult results = new ResponseResult();
		
		List<DataEntity> dataEntities = new ArrayList<DataEntity>();
		ErrorEntity errorEntity = new ErrorEntity();
		errorEntity.setStatus(status);
		errorEntity.setDetail(errorDetails);
		errorEntity.setTitle(title);
		
		DataEntity dataEntity = new DataEntity();
		//dataEntity.setError(errorEntity);
		
		dataEntities.add(dataEntity);
		results.setData(dataEntities);
		
		return results;
	}

}