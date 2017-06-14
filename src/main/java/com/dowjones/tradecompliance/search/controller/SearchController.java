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
@RequestMapping("/search/v1")
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
	@PostMapping("/searchResult")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<FileQueryResults> searchFiles(@Valid @RequestBody FileSearchableData searchableData,
			Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse(ItemConstants.BAD_REQUEST,
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
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse(500, ex.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @Description - To create trade item in elastic search
	 * @param TradeItem
	 *            json
	 * @return Json Response with creation status
	 */
	@PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ItemResponse createFile(@Valid @RequestBody TradeItem file, Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			ItemResponse response = new ItemResponse();
			response.setResponseCode(ItemConstants.BAD_REQUEST);
			response.setMessage("Please check input request and correct -"
					+ errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return response;
		}
		logger.debug("File Create Request");
		try {
			ItemResponse response = fileSearch.createFile(file);
			return response;
		} catch (Exception ex) {
			logger.error("Item creation failure " + ex);
			return null;
		}
	}

	/**
	 * @Description - To create multiple trade items in elastic search
	 * @param List
	 *            of trade items in json format
	 * @return Json Response with creation status
	 */
	@PostMapping("/createbulkItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ItemResponse createBulkFile(@RequestBody @Valid TradeItemList files, Errors errors) {
		// Return error response, if there are any errors in the input request
		if (errors.hasErrors()) {
			ItemResponse response = new ItemResponse();
			response.setResponseCode(ItemConstants.BAD_REQUEST);
			response.setMessage("Please check input request and correct -"
					+ errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
			return response;
		}
		logger.debug("Bulk Item Create Request");
		if (null != files) {
			try {
				ItemResponse response = fileSearch.createBulkFiles(files.getList());
				return response;
			} catch (Exception ex) {
				logger.error("Error while creating bulk items" + ex);
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * @Description - To delete all trade items in elastic search
	 * @param
	 * @return Json Response with creation status
	 */
	@DeleteMapping("/deleteItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public @ResponseBody ItemResponse deleteTradeItems() {
		logger.debug("Delete All Items Request");
		try {
			ItemResponse response = fileSearch.deleteAllItems();
			return response;
		} catch (Exception ex) {
			logger.error("Error while deleting trade items" + ex);
			return null;
		}
	}
}
