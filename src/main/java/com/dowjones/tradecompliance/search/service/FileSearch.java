package com.dowjones.tradecompliance.search.service;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ItemResponse;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeItem;
import com.dowjones.tradecompliance.search.domain.TradeItemRequest;

public interface FileSearch {
	
	ResponseResult searchFiles(FileSearchableData searchableData) throws Exception;
	
	ItemResponse createFile(TradeItem file) throws Exception;
	
	ItemResponse createBulkFiles(List<TradeItemRequest> list) throws Exception;
	
	ItemResponse deleteAllItems() throws Exception;
	
}
