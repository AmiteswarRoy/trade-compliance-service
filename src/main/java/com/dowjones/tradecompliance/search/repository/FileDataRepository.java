package com.dowjones.tradecompliance.search.repository;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileDataRepository {

	ResponseResult searchFiles(FileSearchableData searchableData) throws Exception;
	
	ItemResponse createFile(TradeItem file) throws Exception;
	
	ItemResponse createBulkFiles(List<TradeItem> files) throws Exception;
	
	ItemResponse deleteAllItems() throws Exception;

}
