package com.dowjones.tradecompliance.search.service;

import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;

public interface FileSearch {
	
	FileQueryResults searchFiles(FileSearchableData searchableData) throws Exception;
}
