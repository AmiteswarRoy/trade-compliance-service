package com.dowjones.tradecompliance.search.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dowjones.tradecompliance.search.aop.EnableInstrumentation;
import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.repository.FileDataRepository;
import com.dowjones.tradecompliance.search.service.FileSearch;

/**
 * 
 * @Description service implementation for creating and seaching trade items 
 * @author Infosys
 *
 */
@Service
@Qualifier("FileSearchService")
public class FileSearchService implements FileSearch {
	private Logger logger = LogManager.getLogger(FileSearchService.class);

	@Autowired
	@Qualifier("FileDataRepositoryImpl")
	FileDataRepository repository;

	/**
	 * @Description - Method to search files based on search criteria
	 * @param - FileSearchableData json
	 * @return - FileQueryResults json
	 * @exception java.lang.Exception
	 * */
	@Override
	@EnableInstrumentation
	public FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception {
		logger.debug("Inside file search service method");
		FileQueryResults results = repository.searchFiles(searchableData);
		return results;

	}

	/**
	 * @Description - To create trade item in elastic search
	 * @param - TradeItem 
	 * @return - ItemResponse
	 * @exception java.lang.Exception
	 * */
	@Override
	public ItemResponse createFile(TradeItem file) throws Exception {
		logger.debug("Inside create file service method");
		ItemResponse response = repository.createFile(file);
		return response;

	}

	/**
	 * @Description - To create multiple trade items in elastic search
	 * @param - List<TradeItem> 
	 * @return - ItemResponse
	 * @exception java.lang.Exception
	 * */
	@Override
	public ItemResponse createBulkFiles(List<TradeItem> files) throws Exception {
		logger.debug("Inside create bulk files service method");
		ItemResponse response = repository.createBulkFiles(files);
		return response;

	}
	
	/**
	 * @Description - Deleting all the data in Elastic search
	 * @param -  
	 * @return - ItemResponse
	 * @exception java.lang.Exception
	 * */
	@Override
	public ItemResponse deleteAllItems() throws Exception {
		logger.debug("Inside delete all service method");
		ItemResponse response = repository.deleteAllItems();
		return response;
	}

}
