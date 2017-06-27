package com.dowjones.tradecompliance.search.repository.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dowjones.tradecompliance.search.configuration.ElasticSearchConfig;
import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.util.ItemConstants;

/**
 * @author swathi_c05
 * @Descrpiton To generate query strings
 * 
 */
@Component
public class EsFileQueryBuilder {
	private Logger logger = LogManager.getLogger(EsFileQueryBuilder.class);

	@Autowired
	@Qualifier("FileSearchCriteria")
	private List<CriteriaProcessor<FileSearchableData>> fileSearchCriteria;

	@Autowired
	private ElasticSearchConfig config;

	/**
	 * @Description - Method to build the query
	 * @param FileSearchableData
	 * @return SearchSourceBuilder
	 * @exception java.lang.Exception
	 */
	public SearchSourceBuilder buildFileSearchQuery(FileSearchableData searchableData) throws Exception {
		logger.debug("Into Query builder");

		List<String> invalidCriteria = new ArrayList<String>();
		QueryComponents components = validateAndBuildFileQueryComponents(searchableData, invalidCriteria);
		confirmValidFileQuery(invalidCriteria);

		return buildFileSearchQueryFromComponents(components);
	}

	/**
	 * @Description - Construct ES queries based on criteria
	 * @param FileSearchableData
	 * @return QueryComponents
	 * @exception java.lang.Exception
	 */
	private QueryComponents validateAndBuildFileQueryComponents(FileSearchableData searchableData,
			List<String> invalidCriteria) throws Exception {
		QueryComponents components = new QueryComponents();
		for (CriteriaProcessor<FileSearchableData> processor : fileSearchCriteria) {
			if (processor.hasCriteria(searchableData, invalidCriteria)) {
				processor.processCriteria(searchableData, components);
			}
		}

		return components;
	}

	/**
	 * @Description - To check the invalid query
	 * @param List<String>
	 * @return 
	 * @exception java.lang.Exception
	 */
	private void confirmValidFileQuery(List<String> invalidCriteria) throws Exception {
		if (!invalidCriteria.isEmpty()) {
			StringBuilder message = new StringBuilder("Invalid Search Criteria:");
			for (String reason : invalidCriteria) {
				message.append(reason).append(";");
			}
			logger.error(message);
			throw new Exception();
		}
	}

	/**
	 * @Description - Executing the constructed query
	 * @param QueryComponents
	 * @return SearchSourceBuilder
	 * @exception java.lang.Exception
	 */
	private SearchSourceBuilder buildFileSearchQueryFromComponents(QueryComponents components) throws Exception {
		SearchSourceBuilder search = new SearchSourceBuilder();

		search.fetchSource(ItemConstants.SEARCHABLE_FIELDS, null);
		search.query(components.getBoolQuery());
		search.size(config.getMaxResultsToFetch());

		return search;
	}

}
