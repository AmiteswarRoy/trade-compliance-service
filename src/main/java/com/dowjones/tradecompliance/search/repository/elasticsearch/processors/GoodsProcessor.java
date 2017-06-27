package com.dowjones.tradecompliance.search.repository.elasticsearch.processors;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dowjones.tradecompliance.search.domain.FileSearchableData;
import com.dowjones.tradecompliance.search.domain.SearchDataCriteria;
import com.dowjones.tradecompliance.search.repository.elasticsearch.CriteriaProcessor;
import com.dowjones.tradecompliance.search.repository.elasticsearch.QueryComponents;
import com.dowjones.tradecompliance.search.util.ItemConstants;

/**
 * 
 * @Deccription Constructing the query builder for goods searchcritera
 * @author swathi_c05
 * 
 * */
@Component
@Qualifier("FileSearchCriteria")
public class GoodsProcessor implements CriteriaProcessor<FileSearchableData> {
	
	/**
	 * @Description Validate criteria against null and blank
	 * @param FileSearchableData, List<String>
	 * @return boolean
	 * */
	@Override
	public boolean hasCriteria(FileSearchableData data, List<String> invalidCriteria) {
		
		for(SearchDataCriteria criteria : data.getData()) {
			List<String> goods = criteria.getAttributes().getFilters().getGoods();

			if(goods!=null && goods.size()>0) {
				for(String good: goods) {
					if(StringUtils.isEmpty(good) || StringUtils.isBlank(good)) {
						invalidCriteria.add("Cannot have an empty good.");
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @Description Construct the multi match query for list of goods
	 * @param FileSearchableData, QueryComponents
	 * @return 
	 * */
	@Override
	public void processCriteria(FileSearchableData data, QueryComponents components) {
		BoolQueryBuilder boolQuery = new BoolQueryBuilder();
		List<MultiMatchQueryBuilder> multiQuery = new ArrayList<MultiMatchQueryBuilder>();
		
		for(SearchDataCriteria criteria : data.getData()) {
			List<String> goods = criteria.getAttributes().getFilters().getGoods();
			
			for(String good: goods) {
				multiQuery.add(QueryBuilders.multiMatchQuery(good, ItemConstants.SEARCHABLE_FIELDS).fuzziness("AUTO"));
			}
		}
		
		for(MultiMatchQueryBuilder multiMatchQuery : multiQuery) {
			boolQuery.should(multiMatchQuery);
		}
		
		components.setBoolQuery(boolQuery);
	}

}
