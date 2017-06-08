package com.dowjones.tradecompliance.search.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.BuildResponseUtil;

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
	 * @param FileSearchableData json
	 * @return FileQueryResults json
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/searchData")
	@Consumes(MediaType.APPLICATION_JSON)
	@EnableInstrumentation
	public ResponseEntity<FileQueryResults> searchFiles(@RequestBody FileSearchableData searchableData) {
		logger.debug("Inside file search method");
		try {
			FileQueryResults results = fileSearch.searchFiles(searchableData);
			logger.debug("File search result hits :"+results.getHits());
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createSuccessfulResponse(results);
		}  catch(Exception ex) {
			logger.error("File search failed " +ex);
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse(500, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @Description - To create trade item in elastic seach
	 * @param TradeItem json
	 * @return Json Response with creation status
	 */
	@PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ItemResponse createFile(@RequestBody TradeItem file) {
		logger.debug("File Create Request");
		try {
			ItemResponse response = fileSearch.createFile(file);
			return response;
		}  catch(Exception ex) {
			logger.error("Item creation failure " +ex);
			return null;
		}
	}
	
	/**
	 * @Description - To create multiple trade items in elastic search
	 * @param List of trade items in json format
	 * @return Json Response with creation status
	 */
	@PostMapping("/createbulkItems")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ItemResponse createBulkFile(@RequestBody List<TradeItem> files) {
		logger.debug("Bulk Item Create Request");
		try {
			ItemResponse response = fileSearch.createBulkFiles(files);
			return response;
		}  catch(Exception ex) {
			logger.error("Error while creating bulk items"+ex);
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
	public @ResponseBody ItemResponse deleteTradeItems() {
		logger.debug("Delete All Items Request");
		try {
			ItemResponse response = fileSearch.deleteAllItems();
			return response;
		}  catch(Exception ex) {
			logger.error("Error while deleting trade items"+ex);
			return null;
		}
	}
}
