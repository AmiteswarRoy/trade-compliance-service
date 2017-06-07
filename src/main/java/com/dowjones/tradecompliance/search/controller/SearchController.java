package com.dowjones.tradecompliance.search.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemCreationResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.BuildResponseUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 
 * @author Infosys
 *
 */
@RestController
@RequestMapping("/search/v1")
public class SearchController {
	//private static Logger logger = LogManager.getLogger(SearchController.class);
	private static Gson gson = new GsonBuilder().create();
	
	@Autowired
	@Qualifier("FileSearchService")
	FileSearch fileSearch;
	
	/**
	 * @Desc - Hello end point to check connectivity
	 * @return
	 */
	@GetMapping("/hello")
	public ResponseEntity hello() {
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
	public ResponseEntity<FileQueryResults> getFileQueryResults(@RequestBody FileSearchableData searchableData) {
		System.out.println("File Create Request");
		try {
			FileQueryResults results = fileSearch.searchFiles(searchableData);
			System.out.println("File Create Request successfully processed :"+results.getTotalHits() +","+ results.getFiles().size());
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createSuccessfulResponse(results);
		}  catch(Exception ex) {
			System.out.println("File Index creation failure " +ex);
			return (ResponseEntity<FileQueryResults>) BuildResponseUtil.createErrorResponse("500", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * @Description - To create trade item in elastic search
	 * @param TradeItem json
	 * @return Json Response with creation status
	 */
	@PostMapping("/createItem")
	@Consumes(MediaType.APPLICATION_JSON)
	public @ResponseBody ItemCreationResponse createFile(@RequestBody TradeItem file) {
		System.out.println("File Create Request");
		try {
			ItemCreationResponse response = fileSearch.createFile(file);
			System.out.println("File Create Request successfully processed");
			return response;
		}  catch(Exception ex) {
			System.out.println("File Index creation failure " +ex);
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
	public @ResponseBody ItemCreationResponse createBulkFile(@RequestBody List<TradeItem> files) {
		System.out.println("Bulk Item Create Request");
		try {
			ItemCreationResponse response = fileSearch.createBulkFiles(files);
			return response;
		}  catch(Exception ex) {
			return null;
		}
	}
}
