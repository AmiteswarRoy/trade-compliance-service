package com.dowjones.tradecompliance.search.repository;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemCreationResponse;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileDataRepository {

	FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception;
	
	ItemCreationResponse createFile(TradeItem file) throws Exception;
	
	ItemCreationResponse createBulkFiles(List<TradeItem> files) throws Exception;

}
