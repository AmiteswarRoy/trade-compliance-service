package com.dowjones.tradecompliance.search.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.service.FileSearch;
import com.dowjones.tradecompliance.search.util.BuildResponseUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@RestController
@RequestMapping("/search/v1")
public class SearchController {
	//private static Logger logger = LogManager.getLogger(SearchController.class);
	private static Gson gson = new GsonBuilder().create();
	
	@Autowired
	@Qualifier("FileSearchService")
	FileSearch fileSearch;
	
	@GetMapping("/hello")
	public ResponseEntity hello() {
		return BuildResponseUtil.createSuccessfulResponse("Greetings from Search Service");
	}
	
	@PostMapping("/file/file/searchGoods")
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
	
	/*@PostMapping("/files")
	@Consumes(MediaType.APPLICATION_JSON)
	//@EnableInstrumentation
	public void bulkInsertion(@RequestBody FileData file) {
		logger.info("File Create Request");
		if (logger.isTraceEnabled()) {
			logger.trace("File Create Request: {}", gson.toJson(file));
		}
		try {
			fileSearch.bulkInsertion(file);
			logger.info("File Create Request successfully processed");
		} catch(Exception ex) {
			logger.error("File Index creation failure ", ex);
		} 
	}*/
}
