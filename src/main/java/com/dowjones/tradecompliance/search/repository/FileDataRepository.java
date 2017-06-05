package com.dowjones.tradecompliance.search.repository;

import com.dowjones.tradecompliance.search.domain.FileQueryResults;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;

public interface FileDataRepository {

	/*void createFile(FileData file) throws Exception;
	//void updateFile(FileData file) throws Exception;
*/	FileQueryResults getFiles(FileSearchableData searchableData) throws Exception;

}
