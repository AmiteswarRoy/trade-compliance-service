package com.dowjones.tradecompliance.search.repository.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.lucene.queryparser.xml.QueryBuilderFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;

public class EsFileQueryBuilder {
	private Logger logger = LogManager.getLogger(EsFileQueryBuilder.class);

	public SearchSourceBuilder buildFileSearchQuery(FileSearchableData criteria) throws Exception {
		logger.debug("Into Query builder");
		List<String> invalidCriteria = new ArrayList<String>();
		
		/*QueryComponents components = validateAndBuildFileQueryComponents(criteria, invalidCriteria);
		
		confirmValidFileQuery(invalidCriteria);
		
		QueryBuilders.mulmultiMatchQuery(components, fieldNames);*/
		
		return null;
	}
	
}
