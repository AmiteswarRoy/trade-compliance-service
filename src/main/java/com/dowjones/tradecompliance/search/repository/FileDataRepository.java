package com.dowjones.tradecompliance.search.repository;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileDataRepository {

	ResponseResult searchFiles(FileSearchableData searchableData) throws Exception;
	
	//ItemResponse createFile(TradeItem file) throws Exception;
	
	String createBulkFiles(List<TradeItem> items) throws Exception;
	
	String deleteAllItems() throws Exception;

}
