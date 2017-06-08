package com.dowjones.tradecompliance.search.repository;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileDataRepository {

	FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception;
	
	ItemResponse createFile(TradeItem file) throws Exception;
	
	ItemResponse createBulkFiles(List<TradeItem> files) throws Exception;
	
	ItemResponse deleteAllItems() throws Exception;

}
