package com.dowjones.tradecompliance.search.service;

import java.util.List;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.ResponseResult;
import com.dowjones.tradecompliance.search.domain.TradeItem;

public interface FileSearch {
	
	ResponseResult searchFiles(FileSearchableData searchableData) throws Exception;
	
	//ItemResponse createFile(TradeItem file) throws Exception;
	
	String createBulkFiles(List<TradeItem> list) throws Exception;
	
	String deleteAllItems() throws Exception;
	
}
