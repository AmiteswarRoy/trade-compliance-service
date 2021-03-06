package com.dowjones.tradecompliance.search.controller;

import java.util.stream.Collector;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.domain.TradeItemList;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.BuildResponseUtil;
import com.dowjones.tradecompliance.search.util.ItemConstants;

/**
 * 
 * @author Infosys
 *
 */
@RestController
@RequestMapping("/tradecompliance/v1")
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
	 * @return FileQueryResults json
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/search")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<FileQueryResults> searchFiles(@Valid @RequestBody FileSearchableData searchableData,
			Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		logger.debug("Inside file search method");
		try {
			FileQueryResults results = fileSearch.searchFiles(searchableData);
			logger.debug("File search result hits :" + results.getHits());
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createSuccessfulResponse(results);
		} catch (Exception ex) {
			logger.error("File search failed " + ex);
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse(ItemConstants.SERVER_DOWN,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Description - To create trade item in elastic search
	 * @param TradeItem
	 *            json
	 * @return Json Response with creation status
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ResponseEntity<ItemResponse> createFile(@Valid @RequestBody TradeItem file, Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		logger.debug("File Create Request");
		try {
			ItemResponse response = fileSearch.createFile(file);
			if(null != response){
				return (ResponseEntity<ItemResponse>) BuildResponseUtil.createSuccessfulResponse(response);
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
	public @ResponseBody ResponseEntity<ItemResponse> createBulkFile(@RequestBody @Valid TradeItemList files, Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(
					errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")),
					HttpStatus.BAD_REQUEST);
		}
		logger.debug("Bulk Item Create Request");
		if (null != files && files.getList().size() > 0 ) {
			if(null != files.getList().get(0)){
				try {
					ItemResponse response = fileSearch.createBulkFiles(files.getList());
					if(null != response){
						return (ResponseEntity<ItemResponse>) BuildResponseUtil.createSuccessfulResponse(response);
					}else{
						return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE, HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
				} catch (Exception ex) {
					logger.error("Error while creating bulk items" + ex);
					return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.ITEM_CREATION_FAILURE+" due to "+ex,
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}else{
				return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.NO_ITEMS,
						HttpStatus.BAD_REQUEST);

			}

		}else{
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.NO_ITEMS,
					HttpStatus.BAD_REQUEST);
		} 

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
	public @ResponseBody ResponseEntity<ItemResponse> deleteTradeItems() {
		logger.debug("Delete All Items Request");
		try {
			ItemResponse response = fileSearch.deleteAllItems();
			if(null != response){
				return (ResponseEntity<ItemResponse>) BuildResponseUtil.createSuccessfulResponse(response);
			}else{
				return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.DELETE_FAILURE,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		} catch (Exception ex) {
			logger.error("Error while deleting trade items" + ex);
			return (ResponseEntity<ItemResponse>) BuildResponseUtil.createErrorResponse(ItemConstants.DELETE_FAILURE+" due to "+ex,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}