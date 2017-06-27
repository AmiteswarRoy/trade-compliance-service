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
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.ErrorEntity;
import com.dowjones.tradecompliance.search.domain.ErrorResponse;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.MetaData;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.UploadRequest;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.ItemConstants;
import com.dowjones.tradecompliance.search.util.SearchResultException;

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
	@Qualifier("FileSearchImpl")
	FileSearch fileSearch;

	/**
	 * @Desc - Hello end point to check connectivity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping("/hello")
	public ResponseEntity hello() {
		logger.debug("In Hello endpoint");
		return new ResponseEntity("Greetings from Trade Compliance Service", HttpStatus.OK);
	}

	/**
	 * @Description - To search the data based on search criteria
	 * @param FileSearchableData
	 *            json
	 * @return ResponseEntity json
	 */
	@PostMapping("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<?> searchFiles(@Valid @RequestBody FileSearchableData searchableData, Errors errors) {

		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			String errorDetails = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(","));
			return new ResponseEntity<>(buildErrorResponse("400", errorDetails), HttpStatus.BAD_REQUEST);
		}
		logger.debug("Inside file search method");
		ResponseResult results = new ResponseResult();
		try {
			results = fileSearch.searchFiles(searchableData);
			logger.debug("File search result hits :" + results.getMeta().getCount());
			return new ResponseEntity<>(results, HttpStatus.OK);

		} catch (SearchResultException e) {
			logger.error("File search failed " + e);
			return new ResponseEntity<>(buildErrorResponse(Integer.toString(e.getResponseCode()), e.getErrorMessage()),
					HttpStatus.valueOf(e.getResponseCode()));
		} catch (Exception ex) {
			logger.error("File search failed " + ex);
			return new ResponseEntity<>(buildErrorResponse("500", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @Description - To create trade item in elastic search
	 * @param TradeItem
	 *            json
	 * @return Json Response with creation status
	 */
	/*@SuppressWarnings("unchecked")
	// @PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ResponseEntity<ItemResponse> createFile(@Valid @RequestBody UploadRequest uploadRequest,
			Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		logger.debug("File Create Request");
		try {
			if (null != uploadRequest && !CollectionUtils.isEmpty(uploadRequest.getData())) {
				TradeItem file = uploadRequest.getData().get(0);
				ItemResponse response = fileSearch.createFile(file);
				if (null != response) {
					return (ResponseEntity<ItemResponse>) BuildResponseUtil.createSuccessfulResponse(response);
				} else {
					return (ResponseEntity<ItemResponse>) BuildResponseUtil
							.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} else {
				return (ResponseEntity<ItemResponse>) BuildResponseUtil
						.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception ex) {
			logger.error("Item creation failure " + ex);
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(
					ItemConstants.ITEM_CREATION_FAILURE + " due to " + ex, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/**
	 * @Description - To create multiple trade items in elastic search
	 * @param List
	 *            of trade items in json format
	 * @return Json Response with creation status
	 */
	@PostMapping("/upload")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<?> createBulkFile(@RequestBody @Valid UploadRequest uploadRequest, Errors errors) {

		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			String errorDetails = errors.getAllErrors().stream().map(x -> x.getDefaultMessage())
					.collect(Collectors.joining(","));
			return new ResponseEntity<>(buildErrorResponse("400", errorDetails), HttpStatus.BAD_REQUEST);
		}
		logger.debug("Bulk Item Create Request");
		if (null != uploadRequest && null != uploadRequest.getData() && uploadRequest.getData().size() > 0) {
			try {
				String totalItemsCreated = fileSearch.createBulkFiles(uploadRequest.getData());
				if (null != totalItemsCreated) {
					ResponseResult responseResult = new ResponseResult();
					MetaData metaData = new MetaData();
					metaData.setCount(totalItemsCreated);
					responseResult.setMeta(metaData);
					return new ResponseEntity<>(responseResult, HttpStatus.OK);

				} else {
					return new ResponseEntity<>(buildErrorResponse("500", ItemConstants.ITEM_CREATION_FAILURE),
							HttpStatus.INTERNAL_SERVER_ERROR);

				}

			} catch (Exception ex) {
				logger.error("Error while creating bulk items" + ex);
				return new ResponseEntity<>(buildErrorResponse("500", ex.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(buildErrorResponse("400", ItemConstants.NO_ITEMS), HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * @Description - To delete all trade items in elastic search
	 * @param
	 * @return Json Response with creation status
	 */
	@DeleteMapping("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<?> deleteTradeItems() {
		logger.debug("Delete All Items Request");
		try {
			String itemsDeleted = fileSearch.deleteAllItems();
			if (null != itemsDeleted) {
				ResponseResult responseResult = new ResponseResult();
				MetaData metaData = new MetaData();
				metaData.setCount(itemsDeleted);
				responseResult.setMeta(metaData);
				return new ResponseEntity<>(responseResult, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(buildErrorResponse("500", ItemConstants.DELETE_FAILURE),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception ex) {
			logger.error("Error while deleting trade items" + ex);
			return new ResponseEntity<>(buildErrorResponse("500", ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	/**
	 * @Description - To build the error scenario
	 * @param
	 * @return ErrorResponse
	 */
	private ErrorResponse buildErrorResponse(String status, String errorDetails) {

		ErrorResponse response = new ErrorResponse();

		List<ErrorEntity> errorList = new ArrayList<ErrorEntity>();
		ErrorEntity errorEntity = new ErrorEntity();
		errorEntity.setStatus(status);
		errorEntity.setDetail(errorDetails);

		errorList.add(errorEntity);
		response.setErrors(errorList);

		return response;
	}

}