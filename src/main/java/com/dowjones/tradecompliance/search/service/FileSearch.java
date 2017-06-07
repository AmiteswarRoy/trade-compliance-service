package com.dowjones.tradecompliance.search.service;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemCreationResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileSearch {
	
	FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception;
	
	ItemCreationResponse createFile(TradeItem file) throws Exception;
	
	ItemCreationResponse createBulkFiles(List<TradeItem> files) throws Exception;
	
}
